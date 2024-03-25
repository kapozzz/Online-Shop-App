package com.kapozzz.client.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val isFirstStart = booleanPreferencesKey("is_first_start")

class SettingsDataStore(
    private val context: Context
) {

    val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    suspend fun itStarted() {
        context.settingsDataStore.edit { settings ->
            settings[isFirstStart] = false
        }
    }

    suspend fun isFirstStart(): Boolean {
        return context.settingsDataStore.data
            .map { settings ->
                settings[isFirstStart] ?: true
            }.first()
    }

}