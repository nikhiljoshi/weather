package com.nikhil.test.data.local

import com.nikhil.test.data.local.entity.FavCityEntity


interface DatabaseHelper {
    suspend fun getCityList(): List<FavCityEntity>
    suspend fun insertAll(albumEntity: List<FavCityEntity>)
}