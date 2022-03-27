package com.thor.githubuser3.UI

import com.thor.githubuser3.UI.Home.HomeUseCase
import com.thor.githubuser3.UI.Home.HomeUseCaseImplement
import com.thor.githubuser3.UI.Home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    single<HomeUseCase> { HomeUseCaseImplement(get()) }
    viewModel { HomeViewModel(get()) }

//    single<DetailUserUseCase> { DetailUserUseCaseImplement(get()) }
//    viewModel { DetailUserViewModel(get()) }
//
//    single<ProfileTabUseCase> { ProfileTabUseCaseImplement(get()) }
//    viewModel { ProfileTabViewModel(get()) }
//
//    single<FavoriteUseCase> { FavoriteUseCaseImplement(get()) }
//    viewModel { FavoriteViewModel(get()) }
}