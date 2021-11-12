package com.olayg.halfwayapp.model.response

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class CharacterResponse(
    @Json(name = "fallspeed")
    val fallSpeed: String,
    val game: String,
    val gifs: List<Gif>,
    val guide: String,
    val id: String,
    val name: String,
    @Json(name = "tierdata")
    val tierData: String,
    @Json(name = "walljump")
    val wallJump: String,
    val weight: String
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Gif(
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "dataid")
    val dataId: String,
    val description: String,
    val id: String,
    val score: String,
    val source: String,
    @Json(name = "submittedby")
    val submittedBy: String,
    @Json(name = "typeid")
    val typeId: String,
    @Json(name = "updated_at")
    val updatedAt: String,
    val url: String
) : Parcelable
