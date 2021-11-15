package com.olayg.halfwayapp.repo

import android.util.Log
import com.olayg.halfwayapp.model.custom.Character
import com.olayg.halfwayapp.model.response.CharacterResponse
import com.olayg.halfwayapp.repo.remote.RetrofitInstance

object SSBRepo {

    private val smashBrosUnofficialService by lazy { RetrofitInstance.smashBrosUnofficialService }
    private val smashLoungeService by lazy { RetrofitInstance.smashLoungeService }

    suspend fun getAllCharacters() = try {
        Log.d("Repo","getImage")
        smashLoungeService.getAllCharacters().map { characterResponse ->
            Character.convertToCharacter(characterResponse, getImage(characterResponse))
        }
    } catch (ex: Exception) {
        emptyList()
    }

    private suspend fun getImage(character: CharacterResponse) = try {
        Log.d("Repo",character.name)
        smashBrosUnofficialService.getAllCharacters(character.name).firstOrNull()?.image
    } catch (ex: Exception) {
        null
    }
}