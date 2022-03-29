package com.thor.githubuser3.Base

import android.app.Application
import com.thor.githubuser3.BuildConfig
import com.thor.githubuser3.Data.Local.daoModule
import com.thor.githubuser3.Data.Local.localModule
import com.thor.githubuser3.Data.API.errorHandleModule
import com.thor.githubuser3.Data.API.remoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

abstract class BaseApplication : Application() {

    abstract fun defineDependencies(): List<Module>

    override fun onCreate() {
        super.onCreate()
        dependenciesInjection()
    }

    private fun dependenciesInjection() {
        startKoin {

            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)

            androidContext(this@BaseApplication)

            modules(
                mutableListOf(
                    daoModule,
                    remoteModule,
                    localModule,
                    errorHandleModule
                )
                    .apply { addAll(defineDependencies()) }
            )
        }
    }

}