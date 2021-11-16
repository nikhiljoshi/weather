package com.nikhil.test.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikhil.test.data.local.DatabaseHelperImpl
import com.nikhil.test.dataprovider.remote.network.RemoteDataProviderImpl
import javax.inject.Inject

/**
 * Factory class for ViewModels
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(application: Application,dataProviderImpl: RemoteDataProviderImpl,
                                           databaseHelperImpl: DatabaseHelperImpl) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass === WeatherViewModel::class -> modelClass as T
            else -> throw IllegalArgumentException("unable to find ViewModel class")
        }
    }
}