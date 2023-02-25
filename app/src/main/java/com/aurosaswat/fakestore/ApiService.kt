package com.aurosaswat.fakestore

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/products")
//    Next, create an interface that defines the API endpoints using Retrofit annotations:
     fun getPosts():Call<MutableList<PostModel>>

}