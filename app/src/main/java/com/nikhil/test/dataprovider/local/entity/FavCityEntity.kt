package com.nikhil.test.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavCityEntity(
    @ColumnInfo(name = "name") val name: String,
    @PrimaryKey @ColumnInfo(name = "cityid") val cityid: Int,
    @ColumnInfo(name = "temp") val temp: Double
)


