package com.olayg.halfwayapp.repo.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.olayg.halfwayapp.view.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DataStorePref(val context: Context) {
    private val charName = stringPreferencesKey("character_name")
    private val charGuide = stringPreferencesKey("character_guide")
    suspend fun setCharStringData(name: String, guide: String) {
        context.dataStore.edit { preferences ->
            preferences[charName] = name
            preferences[charGuide] = guide
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
}