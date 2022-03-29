package com.thor.githubuser3.Repository

import com.thor.githubuser3.Repository.User.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { UserRepository(get(), get()) }
}