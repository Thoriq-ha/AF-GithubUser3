package com.thor.githubuser3.Repository.User

import org.koin.dsl.module

val repositoryModule = module {
    single { UserRepository(get()) }
}