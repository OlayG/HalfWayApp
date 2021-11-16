package com.olayg.halfwayapp.repo.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.olayg.halfwayapp.view.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DataStorePref(val context: Context) {
    private val charName = stringPreferencesKey("character_name")
    private val charGuide = stringPreferencesKey("character_guide")
    private val charGifList = stringSetPreferencesKey("character_gifs")
    suspend fun setCharStringData(name: String, guide: String, gifs: Set<String>) {
        context.dataStore.edit { preferences ->
            preferences[charName] = name
            preferences[charGuide] = guide
            preferences[charGifList] = gifs
        }
    }

    suspend fun getCharName(): String? {
        return context.dataStore.data.map {
            it[charName]
        }.first()
    }

    suspend fun getCharGuide(): String? {
        return context.dataStore.data.map {
            it[charGuide]
        }.first()
    }

    suspend fun getCharGifList(): Set<String>? {
        return context.dataStore.data.map {
            it[charGifList]
        }.first()
    }
}