package com.example.androidchallenge.contracts

import com.example.androidchallenge.model.network.datamodel.DailyWeather
import com.example.androidchallenge.model.network.datamodel.WeatherData
import java.security.AccessControlContext

interface WeatherContract {
    interface WeatherModel{
        fun getWeatherDataFromLatAndLong(lat:String, long:String, presenter:MainActivityPresenter)
    }
    interface MainActivityView{
        fun showWeather(weatherData: WeatherData)
        fun showFailureMessage(s: String)
        fun showLoader()
        fun dismissLoader()
    }
    interface MainActivityPresenter{
        fun getWeatherDataFromLatAndLong(lat:String, long:String)
        fun showWeatherData(body: WeatherData?)
        fun showLoader()
        fun dismissLoader()
        fun showFailureMessage()
    }
}