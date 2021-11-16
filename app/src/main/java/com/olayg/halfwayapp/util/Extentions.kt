package com.olayg.halfwayapp.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.olayg.halfwayapp.R

private const val TAG = "Extentions"
val ViewGroup.layoutInflater: LayoutInflater get() = LayoutInflater.from(context)

fun ImageView.loadUrl(url: String?) {
    Glide.with(this).load(url).into(this)
}

fun SharedPreferences.writeChars(context: Context?, jsonCharacters: String){
    this.edit().apply{
        putString(            context?.resources?.getString(R.string.characters_preference),
            jsonCharacters).apply()
    }
}

fun SharedPreferences.readChars(context: Context?) : String? {
    val st = this.getString(context?.resources?.getString(R.string.characters_preference),"")
    Log.d(TAG, "readChars: $st")
    return st
}