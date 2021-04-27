package com.example.androidchallenge.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidchallenge.R
import com.example.androidchallenge.adapters.ForecastAdapter
import com.example.androidchallenge.model.network.datamodel.DailyWeather
import kotlinx.android.synthetic.main.activity_forecast.*


class ForecastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)
        toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        toolbar.setNavigationOnClickListener { finish() }
        val list = intent.getParcelableArrayListExtra<DailyWeather>("data")
        showForecastData(list)
    }

    private fun showForecastData(list: ArrayList<DailyWeather>?) {
        rv_forecast.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = list?.let { ForecastAdapter(context, it) }
        }
    }

}