package com.nikhil.test.dataprovider.remote.network

import com.nikhil.test.models.CityWeather
import javax.inject.Inject

class RemoteDataProviderImpl @Inject constructor(private val restAPI: RestAPI) :
    RemoteDataProviderInterface {

    suspend override  fun getCityWeather(cityname:String, apikey:String): CityWeather{
        return restAPI.getCityWeather(cityname,apikey)
    }


}