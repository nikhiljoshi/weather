package com.nikhil.test.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nikhil.test.R
import com.nikhil.test.data.local.DatabaseHelperImpl
import com.nikhil.test.data.local.entity.FavCityEntity
import com.nikhil.test.dataprovider.remote.network.RemoteDataProviderImpl
import com.nikhil.test.models.CityWeather
import com.nikhil.test.utils.Constants
import com.nikhil.test.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(application: Application, private val dataProviderImpl: RemoteDataProviderImpl,
                                           private val databaseHelperImpl: DatabaseHelperImpl) : AndroidViewModel(application) {

    val weatherMutableLiveData: MutableLiveData<CityWeather> = MutableLiveData()
    val message: MutableLiveData<Event<Int>> = MutableLiveData()
    val favCityMutableLiveData: MutableLiveData<List<FavCityEntity>> = MutableLiveData()

    fun getWeather(cityName:String){
        viewModelScope.launch {
            try {

                Timber.d("fetchFromRemote")
                fetchFromRemote(cityName)



            }catch (e:Exception){
                Timber.e(e.toString())
                message.postValue(Event(R.string.list_error_message))
            }
        }

    }



    private suspend fun fetchFromRemote(cityName:String) {
        val resultFromApi = getWeatherFromRemote(cityName)
        weatherMutableLiveData.value = resultFromApi
        Timber.d("resultFromApi"+resultFromApi.toString())
    }

    suspend fun getWeatherFromRemote(cityName:String): CityWeather {
        return dataProviderImpl.getCityWeather(cityName, Constants.API_KEY)
    }

    fun bookmarkLocation(name: String, id:Int, temp: Double) {
        viewModelScope.launch {
            val cityInsertInDB = mutableListOf<FavCityEntity>()
            cityInsertInDB.add(FavCityEntity(name, id,temp))
            databaseHelperImpl.insertAll(cityInsertInDB)
        }
    }

    fun getBookMarks() {
        viewModelScope.launch {
         favCityMutableLiveData.value = databaseHelperImpl.getCityList()
        }
    }

}