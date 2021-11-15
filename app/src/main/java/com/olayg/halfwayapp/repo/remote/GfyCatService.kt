package com.olayg.halfwayapp.repo.remote

import com.olayg.halfwayapp.model.response.GyfCatResponse
import com.olayg.halfwayapp.util.env
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface GfyCatService {
    @Headers(env.AUTH_HEADER)
    @GET("gfycats/{id}")
    suspend fun getGfyCat(
        @Path("id") id: String
    ): GyfCatResponse
}