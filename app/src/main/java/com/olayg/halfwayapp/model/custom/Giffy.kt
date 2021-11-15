package com.olayg.halfwayapp.model.custom

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class giffy(
    val gfyItem : gfyItem
) :Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class pxGif(
    val url : String ?,
    val size : Int ?,
    val width : Int ?,
    val height : Int ?,
):Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class gfyItem(
    val content_urls: ContentUrl ?,
    val avgColor : String ?,
val createDate : Int ?,
val description : String ?,
val extraTitle : String ?,
val frameRate : Double ?,
val gatekeeper : Int ?,
val gfyId : String ?,
val gfyName : String ?,
val gfyNumber : Int ?,
val gif100px : String ?,
val gifSize : Int ?,
val gifUrl : String ?,
val hasAudio : Boolean ?,
val hasTransparency : Boolean ?,
val height : Int ?,
val languageCategories : Array<String> ?,
val likes : Int ?,
val max1mbGif : String ?,
val max2mbGif : String ?,
val max5mbGif : String ?,
val md5 : String ?,
val miniPosterUrl : String ?,
val miniUrl : String ?,
val mobilePosterUrl : String ?,
val mobileUrl : String ?,
val mp4Size : Int ?,
val mp4Url : String ?,
val nsfw : Int ?,
val numFrames : Int ?,
val posterUrl : String ?,
val published : Int ?,
val tags : Array<String> ?,
val thumb100PosterUrl : String ?,
val title : String ?,
val url : String ?,
val username : String ?,
val views : Int ?,
val webmSize : Int ?,
val webmUrl : String ?,
val webpUrl : String ?,
val width : Int ?,
val isSticker : Boolean ?,
):Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class ContentUrl(
    @field:Json(name ="100pxGif")
    val pxGif: pxGif ?,
    val largeGif: pxGif ?,
    val max1mbGif: pxGif ?,
    val max2mbGif: pxGif ?,
    val max5mbGif: pxGif ?,
):Parcelable