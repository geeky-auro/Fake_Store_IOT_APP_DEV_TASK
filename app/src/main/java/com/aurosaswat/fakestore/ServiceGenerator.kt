package com.aurosaswat.fakestore

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceGenerator {
    private val client=OkHttpClient.Builder().build()

    private val retrofit=Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com/products/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T> buildService(service:Class<T>):T{
        return retrofit.create(service)
    }
}