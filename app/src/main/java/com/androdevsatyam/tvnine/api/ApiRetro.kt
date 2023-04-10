package com.androdevsatyam.tvnine.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRetro {

    val BASE_URL = "https://hindi.money9.com/wp-json/money9/"

    fun getConnection(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }
}