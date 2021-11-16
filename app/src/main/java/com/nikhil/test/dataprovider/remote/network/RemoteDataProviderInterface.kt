package com.nikhil.test.dataprovider.remote.network

import com.nikhil.test.models.CityWeather


//Follows repository pattern to fetch data
interface RemoteDataProviderInterface {
    suspend fun getCityWeather(cityname:String, apikey:String): CityWeather

}