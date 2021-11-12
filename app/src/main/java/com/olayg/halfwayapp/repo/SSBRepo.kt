package com.olayg.halfwayapp.repo

import com.olayg.halfwayapp.model.custom.Character
import com.olayg.halfwayapp.model.response.CharacterResponse
import com.olayg.halfwayapp.repo.remote.RetrofitInstance

object SSBRepo {

    private val smashBrosUnofficialService by lazy { RetrofitInstance.smashBrosUnofficialService }
    private val smashLoungeService by lazy { RetrofitInstance.smashLoungeService }

    suspend fun getAllCharacters() = try {
        smashLoungeService.getAllCharacters().map { characterResponse ->
            Character.convertToCharacter(characterResponse, getImage(characterResponse))
        }
    } catch (ex: Exception) {
        emptyList()
    }

    private suspend fun getImage(character: CharacterResponse) = try {
        smashBrosUnofficialService.getAllCharacters(character.name).firstOrNull()?.image
    } catch (ex: Exception) {
        null
    }
}