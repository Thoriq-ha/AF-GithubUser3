package com.thor.githubuser3.UI

import com.thor.githubuser3.UI.DetailUser.DetailUserUseCase
import com.thor.githubuser3.UI.DetailUser.DetailUserUseCaseImplement
import com.thor.githubuser3.UI.DetailUser.DetailUserViewModel
import com.thor.githubuser3.UI.Favorite.FavoriteUseCase
import com.thor.githubuser3.UI.Favorite.FavoriteUseCaseImplement
import com.thor.githubuser3.UI.Favorite.FavoriteViewModel
import com.thor.githubuser3.UI.Home.HomeUseCase
import com.thor.githubuser3.UI.Home.HomeUseCaseImplement
import com.thor.githubuser3.UI.Home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    single<HomeUseCase> { HomeUseCaseImplement(get()) }
    viewModel { HomeViewModel(get()) }

    single<DetailUserUseCase> { DetailUserUseCaseImplement(get()) }
    viewModel { DetailUserViewModel(get()) }

    single<FavoriteUseCase> { FavoriteUseCaseImplement(get()) }
    viewModel { FavoriteViewModel(get()) }
}