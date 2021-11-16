package com.nikhil.test.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nikhil.test.R
import com.nikhil.test.models.CityWeather
import com.nikhil.test.ui.main.adapter.WeatherDiffCallback
import timber.log.Timber

class WeatherInfoListAdapter() : RecyclerView.Adapter<WeatherInfoListAdapter.WeatherViewHolder>() {
    
    private var weatherInfoList: MutableList<CityWeather> = mutableListOf<CityWeather>()
    
    // Inflating layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.weather_row, parent, false)
        return WeatherViewHolder(view)
    }
    
    override fun getItemCount() = weatherInfoList.size
    
    //Binding view to data
    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bindData(weatherInfoList[position])
    }
    
    fun setData(infoList: List<CityWeather>) {

        val diffCallback = WeatherDiffCallback(this.weatherInfoList, infoList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.weatherInfoList.clear()
        this.weatherInfoList.addAll(infoList)
        diffResult.dispatchUpdatesTo(this)

        Timber.d(infoList.toString())
    }
    
    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cityname = itemView.findViewById<AppCompatTextView>(R.id.cityName)
        private val temperature = itemView.findViewById<AppCompatTextView>(R.id.temperature)
        private val weatherDesc = itemView.findViewById<AppCompatTextView>(R.id.weatherDesc)

        fun bindData(cityWeather: CityWeather) {
            (itemView.context.getString(R.string.cityName) +  cityWeather.name).also { cityname.text = it }
            (itemView.context.getString(R.string.temperature) +  cityWeather.main.temp).also { temperature.text = it }
            (itemView.context.getString(R.string.weather) +  cityWeather.weather.get(0).main).also { weatherDesc.text = it }

                  }
    }

}

