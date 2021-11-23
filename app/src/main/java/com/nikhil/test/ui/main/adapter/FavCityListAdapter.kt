package com.nikhil.test.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nikhil.test.R
import com.nikhil.test.data.local.entity.FavCityEntity
import com.nikhil.test.ui.main.adapter.CityDiffCallback
import timber.log.Timber

class FavCityListAdapter() : RecyclerView.Adapter<FavCityListAdapter.CityViewHolder>() {

    private var cityList: MutableList<FavCityEntity> = mutableListOf<FavCityEntity>()

    // Inflating layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.city_row, parent, false)
        return CityViewHolder(view)
    }

    override fun getItemCount() = cityList.size

    //Binding view to data
    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bindData(cityList[position])
    }

    fun setData(infoList: List<FavCityEntity>) {

        //val diffCallback = CityDiffCallback(this.cityList, infoList)
        // val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.cityList.clear()
        this.cityList.addAll(infoList)
        //  diffResult.dispatchUpdatesTo(this)

        Timber.d(infoList.toString())
    }

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cityname = itemView.findViewById<AppCompatTextView>(R.id.cityName)
        private val temperature = itemView.findViewById<AppCompatTextView>(R.id.temperature)

        fun bindData(cityWeather: FavCityEntity) {
            (itemView.context.getString(R.string.cityName) +  cityWeather.name).also { cityname.text = it }
            (itemView.context.getString(R.string.temperature) +  cityWeather.temp).also { temperature.text = it }
        }
    }

}

