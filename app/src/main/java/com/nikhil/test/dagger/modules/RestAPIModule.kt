package com.nikhil.test.dagger.modules

import com.nikhil.test.dataprovider.remote.network.RemoteDataProviderImpl
import com.nikhil.test.dataprovider.remote.network.RestAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RestAPIModule {
    
    @Provides
    fun getDataProvider(restAPI: RestAPI): RemoteDataProviderImpl {
        return RemoteDataProviderImpl(restAPI)
    }
}