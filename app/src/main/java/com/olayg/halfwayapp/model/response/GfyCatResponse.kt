package com.olayg.halfwayapp.model.response
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


@JsonClass(generateAdapter = true)
data class GyfCatResponse(
    @Json(name = "gfyItem")
    val gfyItem: GfyItem
)

@JsonClass(generateAdapter = true)
data class GfyItem(
    @Json(name = "max5mbGif")
    val max5mbGif: String
)