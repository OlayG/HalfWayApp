package com.olayg.halfwayapp.repo.remote

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstanceGif {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.gfycat.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val gifService: GifService = retrofit.create(GifService::class.java)
}