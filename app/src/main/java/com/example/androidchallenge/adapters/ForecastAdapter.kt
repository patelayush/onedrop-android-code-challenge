package com.example.androidchallenge.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.view.View
import com.example.androidchallenge.R
import com.example.androidchallenge.model.network.datamodel.DailyWeather
import kotlinx.android.synthetic.main.weather_forecast_row.view.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class ForecastAdapter(private val context:Context, private val list: List<DailyWeather>) : RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {
    class ForecastViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindItems(dailyWeather: DailyWeather) {
            val sdf = SimpleDateFormat("MMM dd")
            var date: Date? = try{
                Date(dailyWeather.dt)
            } catch (exception:Exception){
                null
            }
            if(date != null) {
                itemView.tv_date.text = sdf.format(date)
            }
            itemView.tv_description.text = dailyWeather.weather[0].main
            itemView.tv_temperature.text = "${dailyWeather.temp.min}℉ - ${dailyWeather.temp.max}℉"
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ForecastViewHolder {
        return ForecastViewHolder(LayoutInflater.from(context).inflate(R.layout.weather_forecast_row, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ForecastAdapter.ForecastViewHolder, position: Int) {
        holder.bindItems(list[position])
    }
}