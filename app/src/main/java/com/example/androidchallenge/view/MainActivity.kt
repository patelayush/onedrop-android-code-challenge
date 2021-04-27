package com.example.androidchallenge.view

import android.Manifest
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.androidchallenge.R
import com.example.androidchallenge.contracts.WeatherContract
import com.example.androidchallenge.model.network.datamodel.WeatherData
import com.example.androidchallenge.presenter.MainActivityPresenter
import kotlinx.android.synthetic.main.content_main.*
import java.util.*


class MainActivity : AppCompatActivity(), WeatherContract.MainActivityView {

    private var mainActivityPresenter:MainActivityPresenter? = null
    private var dialog:AlertDialog? = null
    private var lat: String? = null
    private var long: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        mainActivityPresenter = MainActivityPresenter(this, applicationContext)
    }

    private fun getLocation() {
        if(lat== null || long == null) {
            showLoader()
            val lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val location: Location? = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                lat = location?.latitude?.toString()
                long = location?.longitude?.toString()
                mainActivityPresenter!!.getWeatherDataFromLatAndLong(lat!!, long!!)
            } else {
                dialog = AlertDialog.Builder(this)
                    .setTitle("Grant permission")
                    .setMessage("Please grant location permission to the application in settings to fetch weather of your current location.")
                    .setPositiveButton(
                        "Okay"
                    ) { _, _ ->
                        val intent = Intent()
                        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                        val uri =
                            Uri.fromParts("package", this.packageName, null)
                        intent.data = uri
                        startActivity(intent)
                    }
                    .show()
            }
        }
    }

    override fun showWeather(weatherData: WeatherData){
        val geocoder = Geocoder(this, Locale.getDefault())
        try {
            val addresses = geocoder.getFromLocation(weatherData.lat, weatherData.lon, 1)
            tv_location.text = addresses[0].locality
        } catch(exception:Exception){
            Log.d("noddy","Exception occurred while trying to fetch location")
        }
        tv_humidity.text = (weatherData.current.humidity.toString() + "%")
        tv_temperature.text = (weatherData.current.temp.toString() + "\u2109")
        tv_feels_like.text = (weatherData.current.feels_like.toString() + "\u2109")
        tv_wind_speed.text = (weatherData.current.wind_speed.toString() + "mph")
        tv_pressure.text = (weatherData.current.pressure.toString() + " PSI")
        rl_root_layout.setOnClickListener {
            startActivity(Intent(this, ForecastActivity::class.java).putParcelableArrayListExtra("data", weatherData.daily as ArrayList<out Parcelable>))
        }
    }

    override fun showFailureMessage(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    override fun showLoader() {
        rl_progress_layout.visibility = View.VISIBLE
        rl_root_layout.visibility = View.GONE
    }

    override fun dismissLoader() {
        rl_progress_layout.visibility = View.GONE
        rl_root_layout.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        getLocation()
    }
}