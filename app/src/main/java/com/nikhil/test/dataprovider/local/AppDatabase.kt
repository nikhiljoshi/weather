package com.nikhil.test.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nikhil.test.data.local.dao.FavDao
import com.nikhil.test.data.local.entity.FavCityEntity

@Database(entities = [FavCityEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favDao(): FavDao
}