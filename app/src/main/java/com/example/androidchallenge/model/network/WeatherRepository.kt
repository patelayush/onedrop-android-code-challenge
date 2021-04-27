package com.example.androidchallenge.model.network

import com.example.androidchallenge.contracts.WeatherContract
import com.example.androidchallenge.model.network.api.WeatherApi
import com.example.androidchallenge.model.network.api.WeatherApiClient
import com.example.androidchallenge.model.network.datamodel.WeatherData
import retrofit2.Call
import retrofit2.Response

class WeatherRepository:WeatherContract.WeatherModel{

    private  var client:WeatherApi? = null
    init {
        client = WeatherApiClient.client.create(WeatherApi::class.java)
    }
    override fun getWeatherDataFromLatAndLong(lat: String, long: String, presenter: WeatherContract.MainActivityPresenter) {
        presenter.showLoader()
        val call = client?.getWeatherForecast(lat, long)
        call?.enqueue(object: retrofit2.Callback<WeatherData> {
            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                presenter.showFailureMessage()
                presenter.dismissLoader()
            }

            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                handleWeatherDataResponse(response, presenter)
                presenter.dismissLoader()
            }

        })
    }

    private fun handleWeatherDataResponse(
        response: Response<WeatherData>,
        presenter: WeatherContract.MainActivityPresenter
    ) {
        if(response.isSuccessful){
            presenter.showWeatherData(response.body())
        } else{
            presenter.showFailureMessage()
        }
    }
}