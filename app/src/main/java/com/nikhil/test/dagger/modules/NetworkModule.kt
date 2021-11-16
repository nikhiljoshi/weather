package com.nikhil.test.dagger.modules

import com.nikhil.test.dataprovider.remote.network.RestAPI
import com.nikhil.test.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    
    @Provides
    fun getRetrofitInstance(): Retrofit {
        
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    
    @Provides
    fun getRestServiceAPI(): RestAPI = getRetrofitInstance().create(RestAPI::class.java)
}