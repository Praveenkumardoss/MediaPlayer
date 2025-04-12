package com.example.seekho.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: Api = Retrofit.Builder()
        .baseUrl("https://api.jikan.moe/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)
}