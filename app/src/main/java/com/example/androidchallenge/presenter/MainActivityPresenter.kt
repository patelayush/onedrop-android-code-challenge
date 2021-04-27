package com.example.androidchallenge.presenter

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.location.LocationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidchallenge.contracts.WeatherContract
import com.example.androidchallenge.model.network.WeatherRepository
import com.example.androidchallenge.model.network.datamodel.WeatherData


class MainActivityPresenter(view: WeatherContract.MainActivityView, context: Context):WeatherContract.MainActivityPresenter {

    private var model:WeatherContract.WeatherModel = WeatherRepository()
    private var view:WeatherContract.MainActivityView = view
    private var context:Context = context

    override fun getWeatherDataFromLatAndLong(lat: String, long: String) {
        model.getWeatherDataFromLatAndLong(lat, long, this)
    }

    override fun showWeatherData(body: WeatherData?) {
        body?.let { view.showWeather(it) }
    }

    override fun showLoader() {
        view.showLoader()
    }

    override fun dismissLoader() {
        view.dismissLoader()
    }

    override fun showFailureMessage() {
        view.showFailureMessage("The network call was not successful")
    }
}