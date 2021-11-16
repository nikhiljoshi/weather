package com.nikhil.test.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nikhil.test.R
import com.nikhil.test.data.local.DatabaseHelperImpl
import com.nikhil.test.data.local.entity.AlbumEntity
import com.nikhil.test.dataprovider.remote.network.RemoteDataProviderImpl
import com.nikhil.test.models.CityWeather
import com.nikhil.test.utils.Constants
import com.nikhil.test.utils.Event
import com.nikhil.test.utils.Util
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(application: Application, private val dataProviderImpl: RemoteDataProviderImpl,
                                           private val databaseHelperImpl: DatabaseHelperImpl) : AndroidViewModel(application) {

    val weatherMutableLiveData: MutableLiveData<CityWeather> = MutableLiveData()
    val message: MutableLiveData<Event<Int>> = MutableLiveData()


    init{
        viewModelScope.launch {
            try {
                if (Util.isNetworkAvailable(application.applicationContext)) {
                    Timber.d("fetchFromRemote")
                    fetchFromRemote()

                }
//                }else
//                {
//                    Timber.d("fetchFromDB")
//                    if (!databaseHelperImpl.getAlbumList().isEmpty()) {
//                        fetchFromDB()
//                    }
//                    else  message.postValue(Event(R.string.list_error_message))
//
//                }
            }catch (e:Exception){
                Timber.e(e.toString())
                message.postValue(Event(R.string.list_error_message))
            }
        }

    }



    private suspend fun fetchFromRemote() {
        val resultFromApi = getWeatherFromRemote()
        weatherMutableLiveData.value = resultFromApi
        Timber.d("resultFromApi"+resultFromApi.toString())
        //val albumsToInsertInDB = mutableListOf<AlbumEntity>()

//        resultFromApi.forEach { result ->
//            val albums = AlbumEntity(
//                result.id,
//                result.userId,
//                result.title
//            )
//            albumsToInsertInDB.add(albums)
//        }
//        databaseHelperImpl.insertAll(albumsToInsertInDB)
    }

    suspend fun getWeatherFromRemote(): CityWeather {
        return dataProviderImpl.getCityWeather("LONDON", Constants.API_KEY)
    }

    suspend fun getAlbumListDB(): List<AlbumEntity> {
        return databaseHelperImpl.getAlbumList()
    }

}