package com.example.androidchallenge.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidchallenge.R
import com.example.androidchallenge.contracts.WeatherContract
import com.example.androidchallenge.model.network.datamodel.WeatherData
import com.example.androidchallenge.presenter.MainActivityPresenter
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), WeatherContract.MainActivityView {

    private var mainActivityPresenter:MainActivityPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        mainActivityPresenter = MainActivityPresenter(this)
        mainActivityPresenter!!.getWeatherDataFromLatAndLong("40.730610","-73.935242")
    }

    override fun showWeather(weatherData: WeatherData){
        textView.text = (weatherData.current.temp.toString() + " F");
    }

    override fun showFailureMessage(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }
}