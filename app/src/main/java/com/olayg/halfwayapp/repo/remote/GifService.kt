package com.olayg.halfwayapp.repo.remote

//import com.olayg.halfwayapp.model.custom.Giffy
import com.olayg.halfwayapp.model.custom.gfyItem
import com.olayg.halfwayapp.model.custom.giffy
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GifService {
    @GET("/v1/gfycats/{endpoint}")
    suspend fun getGifs(
        @Path("endpoint") endpoint: String,
    ): gfyItem
}