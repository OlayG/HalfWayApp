package com.olayg.halfwayapp.repo.remote

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {

    private const val BASE_URL_SMASH_LOUNGE = "http://smashlounge.com"
    private const val BASE_URL_SMASH_BROS_UNOFFICIAL = "https://smashbros-unofficial-api.vercel.app"
    private const val BASE_URL_GFYCAT = "https://api.gfycat.com/v1/"

    private val smashLoungeRetrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_SMASH_LOUNGE)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val smashBrosUnofficialRetrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_SMASH_BROS_UNOFFICIAL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val gfyCatRetrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_GFYCAT)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val smashLoungeService: SmashLoungeService =
        smashLoungeRetrofit.create(SmashLoungeService::class.java)

    val smashBrosUnofficialService: SmashBrosUnofficialService =
        smashBrosUnofficialRetrofit.create(SmashBrosUnofficialService::class.java)

    val gfyCatService: GfyCatService =
        gfyCatRetrofit.create(GfyCatService::class.java)
}