package com.nikhil.test.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.nikhil.test.data.local.entity.FavCityEntity

@Dao
interface FavDao {

    @Query("SELECT * FROM FavCityEntity")
    suspend fun getAll(): List<FavCityEntity>

    @Insert
    suspend fun insertAll(favEntity : List<FavCityEntity>)

    @Delete
    suspend fun delete(favEntity: FavCityEntity)
}

