package com.olayg.halfwayapp.repo

import com.olayg.halfwayapp.model.custom.Character
import com.olayg.halfwayapp.model.response.CharacterResponse
import com.olayg.halfwayapp.model.response.Gif
import com.olayg.halfwayapp.repo.remote.RetrofitInstance

object SSBRepo {

    private val smashBrosUnofficialService by lazy { RetrofitInstance.smashBrosUnofficialService }
    private val smashLoungeService by lazy { RetrofitInstance.smashLoungeService }
    private val gfyService by lazy { RetrofitInstance.gfyCatService }

    suspend fun getAllCharacters() = try {
        smashLoungeService.getAllCharacters().map { characterResponse ->
            Character.convertToCharacter(characterResponse, getImage(characterResponse), getGfy(characterResponse.gifs))
        }
    } catch (ex: Exception) {
        emptyList()
    }

    private suspend fun getImage(character: CharacterResponse) = try {
        var name = character.name
        if (name == "Mr. Game And Watch") {
            name = "Mr. Game & Watch"
        }
        smashBrosUnofficialService.getAllCharacters(name).firstOrNull()?.image
    } catch (ex: Exception) {
        null
    }

    private suspend fun getGfy(gifs: List<Gif>) = try {
        gifs.map { gif ->
            gif.url = gfyService.getGfyCat(gif.url).gfyItem.max5mbGif
            gif
        }
    } catch (ex: Exception) {
        null
    }
}