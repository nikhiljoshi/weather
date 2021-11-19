package com.nikhil.test.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nikhil.test.models.*

@Entity
data class FavCityEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "cityid") val cityid : Int

)


