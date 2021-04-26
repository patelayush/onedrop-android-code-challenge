package com.example.androidchallenge.contracts

import com.example.androidchallenge.model.network.datamodel.WeatherData

interface WeatherContract {
    interface WeatherModel{
        fun getWeatherDataFromLatAndLong(lat:String, long:String, presenter:MainActivityPresenter)
    }
    interface MainActivityView{
        fun showWeather(weatherData: WeatherData)
        fun showFailureMessage(s: String)
    }
    interface MainActivityPresenter{
        fun getWeatherDataFromLatAndLong(lat:String, long:String)
        fun showWeatherData(body: WeatherData?)
        fun showFailureMessage()
    }
}