package com.example.androidchallenge.presenter

import com.example.androidchallenge.contracts.WeatherContract
import com.example.androidchallenge.model.network.WeatherRepository
import com.example.androidchallenge.model.network.datamodel.WeatherData

class MainActivityPresenter(view: WeatherContract.MainActivityView):WeatherContract.MainActivityPresenter {

    private var model:WeatherContract.WeatherModel = WeatherRepository()
    private var view:WeatherContract.MainActivityView = view

    override fun getWeatherDataFromLatAndLong(lat: String, long: String) {
        model.getWeatherDataFromLatAndLong(lat, long, this)
    }

    override fun showWeatherData(body: WeatherData?) {
        body?.let { view.showWeather(it) }
    }

    override fun showFailureMessage() {
        view.showFailureMessage("The network call was not successful")
    }
}