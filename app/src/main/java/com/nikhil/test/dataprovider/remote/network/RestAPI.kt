package com.nikhil.test.dataprovider.remote.network

import com.nikhil.test.models.CityWeather
import retrofit2.http.GET
import retrofit2.http.Query

//Rest API

interface RestAPI {


  //api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}

    @GET("weather")
    suspend fun getCityWeather(@Query("q") cityname: String?,
                               @Query("appid") apikey: String?): CityWeather

}