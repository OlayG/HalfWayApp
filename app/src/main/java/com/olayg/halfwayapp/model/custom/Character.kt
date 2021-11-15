package com.olayg.halfwayapp.model.custom

import com.olayg.halfwayapp.model.response.CharacterResponse
import com.olayg.halfwayapp.model.response.Gif
import com.olayg.halfwayapp.model.response.Image
import java.io.Serializable

data class Character(
    val name: String,
    val guide: String,
    val image: Image?,
    val rank: String,
    val weightRank: String,
    val speedRank: String,
    val gifs: List<Gif>?
) : Serializable {

    companion object {
        fun convertToCharacter(characterResponse: CharacterResponse, image: Image?, gifs: List<Gif>?) = Character(
            name = characterResponse.name,
            guide = characterResponse.guide,
            image = image,
            rank = characterResponse.tierData,
            weightRank = characterResponse.weight,
            speedRank = characterResponse.fallSpeed,
            gifs = gifs
        )
    }
}