package com.thor.githubuser3.Data.Preference.DataStore

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private val Context.dataStoreThemeUI by preferencesDataStore(name = "setting_theme_ui.pref")

val preferencesModule = module {

    single {
        DataStoreThemeUI(androidApplication().dataStoreThemeUI)
    }

}