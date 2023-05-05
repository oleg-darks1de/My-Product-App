package com.example.phone_specsapp.data.remote

import com.example.phone_specsapp.data.local.Product
import com.example.phone_specsapp.data.local.Products
import retrofit2.http.GET

import retrofit2.http.Path

interface MainApi {
    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product

    @GET("products")
    suspend fun getAllProducts(): Products
}