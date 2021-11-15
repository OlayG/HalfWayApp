package com.olayg.halfwayapp.repo

import android.util.Log
import com.olayg.halfwayapp.model.custom.Character
import com.olayg.halfwayapp.model.response.CharacterResponse
import com.olayg.halfwayapp.repo.remote.RetrofitInstance

object SSBRepo {

    private val smashBrosUnofficialService by lazy { RetrofitInstance.smashBrosUnofficialService }
    private val smashLoungeService by lazy { RetrofitInstance.smashLoungeService }
    private const val TAG = "SSBRepo"
    suspend fun getAllCharacters() = try {
        smashLoungeService.getAllCharacters().map { characterResponse ->
            Log.d(TAG, "getAllCharacters: $characterResponse")
            Character.convertToCharacter(characterResponse, getImage(characterResponse))
        }

    } catch (ex: Exception) {
        Log.d(TAG, "getAllCharacters Exception: $ex")
        emptyList()
    }

    suspend fun getImage(character: CharacterResponse) = try {
        smashBrosUnofficialService.getAllCharacters(character.name).firstOrNull()?.image
    } catch (ex: Exception) {
        null
    }
}