package com.example.apialistirma.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: JsonPlaceholderService by lazy {
        retrofit.create(JsonPlaceholderService::class.java)
    }
}