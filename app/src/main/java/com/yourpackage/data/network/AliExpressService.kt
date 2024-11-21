package com.example.aliexpresspricetracker.data.network

import com.example.aliexpresspricetracker.data.model.ProductDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface AliExpressService {
    @GET("products/{id}")
    suspend fun getProductDetails(@Path("id") productId: String): ProductDetails
}