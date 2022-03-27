package com.thor.githubuser3.Data.Preference.DataStore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


class DataStoreThemeUI(private val dataStore: DataStore<Preferences>) {

    private object Keys {
        val theme = booleanPreferencesKey("is_dark_mode")
    }

    suspend fun setThemeMode(mode: UIMode) {
        dataStore.edit { preferences ->
            preferences[Keys.theme] = when (mode) {
                UIMode.LIGHT -> false
                UIMode.DARK -> true
            }
        }
    }

    val uiModeFlow: Flow<UIMode> = dataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            when (preferences[Keys.theme] ?: false) {
                true -> UIMode.DARK
                false -> UIMode.LIGHT
            }
        }
}

enum class UIMode {
    LIGHT, DARK
}