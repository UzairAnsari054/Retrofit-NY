package com.example.retrofit_ny.data

import com.example.retrofit_ny.data.model.Products
import retrofit2.http.GET

interface ProductsApi {

    @GET("products")
    suspend fun getProducts(): Products
}