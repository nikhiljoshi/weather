package com.nikhil.test.dataprovider.remote.network

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@ExperimentalCoroutinesApi
class RestAPITest {
    
    private lateinit var apiService: RestAPI
    
    private lateinit var mockServer: MockWebServer
    
    @Before
    fun createService() {
        mockServer = MockWebServer()
        mockServer.start(8080)
        val client = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .writeTimeout(1, TimeUnit.SECONDS)
            .build()
        
        apiService = Retrofit.Builder()
            .baseUrl(mockServer.url("/"))
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RestAPI::class.java)
    }
    
    @After
    fun stopService() {
        mockServer.shutdown()
    }



    fun  MockWebServer.enqueueMockResponse(fileName: String, code: Int)
    {
        lateinit var mockResponse: MockResponse
        FileReader.readStringFromFile(fileName)?.let {
            mockResponse = MockResponse()
                .setResponseCode(code)
                .setBody(it)
            enqueue(mockResponse)
            
        }
    }
}
    
