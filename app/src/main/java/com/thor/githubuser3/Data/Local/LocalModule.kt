package com.thor.githubuser3.Data.Local

import androidx.room.Room
import com.thor.githubuser3.Data.Local.UserRoom.UserDatabase
import com.thor.githubuser3.Data.Local.UserRoom.UserLocalConfig
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val daoModule = module {
    factory { get<UserDatabase>().user() }
}

val localModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            UserDatabase::class.java,
            UserLocalConfig.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}