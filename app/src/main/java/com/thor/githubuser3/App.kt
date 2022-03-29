@file:Suppress("unused")
package com.thor.githubuser3

import com.thor.githubuser3.Base.BaseApplication
import com.thor.githubuser3.Data.Local.daoModule
import com.thor.githubuser3.Data.Preference.DataStore.preferencesModule
import com.thor.githubuser3.Data.API.serviceModule
import com.thor.githubuser3.Repository.repositoryModule
import com.thor.githubuser3.UI.ViewModel.viewModelModule
import org.koin.core.module.Module

class App : BaseApplication() {
    override fun defineDependencies(): List<Module> {
        return listOf(
            daoModule,
            serviceModule,
            preferencesModule,
            repositoryModule,
            viewModelModule
        )
    }
}