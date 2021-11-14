package com.olayg.halfwayapp.repo.remote

import com.olayg.halfwayapp.model.response.CharacterResponse
import retrofit2.http.GET

interface SmashLoungeService {

    @GET("chars/all")
    suspend fun getAllCharacters(): List<CharacterResponse>
}