package com.olayg.halfwayapp.repo.remote

import com.olayg.halfwayapp.model.response.CharacterImageResponse
import com.olayg.halfwayapp.model.response.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SmashBrosUnofficialService {


    @GET("/api/v1/ultimate/characters")
    suspend fun getAllCharacters(
        @Query("name") name: String
    ): List<CharacterImageResponse>
}