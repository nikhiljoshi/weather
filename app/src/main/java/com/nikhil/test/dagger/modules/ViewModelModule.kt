package com.nikhil.test.dagger.modules


import android.app.Application
import com.nikhil.test.data.local.DatabaseHelperImpl
import com.nikhil.test.dataprovider.remote.network.RemoteDataProviderImpl
import com.nikhil.test.viewmodel.WeatherViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class ViewModelModule {
    
    @Provides
    fun getWeatherViewModel(application: Application, dataProviderImpl: RemoteDataProviderImpl, databaseHelperImpl: DatabaseHelperImpl): WeatherViewModel {
        return WeatherViewModel(application,dataProviderImpl,databaseHelperImpl)
    }
}

