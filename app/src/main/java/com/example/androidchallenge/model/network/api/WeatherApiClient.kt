package com.example.androidchallenge.model.network.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherApiClient {
    companion object{
        private  var retrofit:Retrofit? = null
        val client:Retrofit get(){
            if(retrofit == null){
                retrofit = Retrofit.Builder()
                        .baseUrl("https://api.openweathermap.org/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit!!
        }
    }
}