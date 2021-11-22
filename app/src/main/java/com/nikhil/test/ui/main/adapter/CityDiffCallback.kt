package com.nikhil.test.ui.main.adapter

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.nikhil.test.data.local.entity.FavCityEntity
import com.nikhil.test.models.CityWeather


class CityDiffCallback(private val oldList: List<FavCityEntity>, private val newList: List<FavCityEntity>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].cityid === newList.get(newItemPosition).cityid
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        val ( value, name) = oldList[oldPosition]
        val ( value1, name1) = newList[newPosition]

        return name == name1 && value == value1
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}