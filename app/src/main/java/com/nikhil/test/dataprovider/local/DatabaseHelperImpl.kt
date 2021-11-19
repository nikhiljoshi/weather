package com.nikhil.test.data.local

import com.nikhil.test.data.local.entity.FavCityEntity
import javax.inject.Inject


class DatabaseHelperImpl @Inject constructor(private val appDatabase: AppDatabase) : DatabaseHelper {
    override suspend fun getCityList(): List<FavCityEntity> = appDatabase.favDao().getAll()
    override suspend fun insertAll(favCity: List<FavCityEntity>) = appDatabase.favDao().insertAll(favCity)
}
