package com.thor.githubuser3

import com.thor.githubuser3.Base.BaseApplication
import com.thor.githubuser3.Data.Local.daoModule
import com.thor.githubuser3.Data.Preference.DataStore.preferencesModul
import com.thor.githubuser3.Data.Remote.serviceModule
import com.thor.githubuser3.Repository.User.repositoryModule
import com.thor.githubuser3.UI.viewModelModule
import org.koin.core.module.Module

class App: BaseApplication() {
    override fun defineDependencies(): List<Module> {
        return listOf(
            daoModule,
            serviceModule,
            preferencesModul,
            repositoryModule,
            viewModelModule
        )
    }
}