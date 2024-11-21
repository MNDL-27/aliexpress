package com.example.aliexpresspricetracker.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    // Replace with the actual base URL of the AliExpress API
    private const val BASE_URL = "https://api.aliexpress.com/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val aliExpressService: AliExpressService by lazy {
        retrofit.create(AliExpressService::class.java)
    }
}