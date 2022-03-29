package com.thor.githubuser3.UI.ViewModel

import com.thor.githubuser3.UI.Page.DetailUser.DetailUserMethod
import com.thor.githubuser3.UI.Page.DetailUser.DetailUserMethodImplement
import com.thor.githubuser3.UI.Page.DetailUser.TabFollow.FollowTabMethod
import com.thor.githubuser3.UI.Page.DetailUser.TabFollow.FollowTabMethodImplement
import com.thor.githubuser3.UI.Page.Favorite.FavoriteMethod
import com.thor.githubuser3.UI.Page.Favorite.FavoriteMethodImplement
import com.thor.githubuser3.UI.Page.Home.HomeMethod
import com.thor.githubuser3.UI.Page.Home.HomeMethodImplement
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    single<HomeMethod> { HomeMethodImplement(get()) }
    viewModel { HomeViewModel(get()) }

    single<DetailUserMethod> { DetailUserMethodImplement(get()) }
    viewModel { DetailUserViewModel(get()) }

    single<FollowTabMethod> { FollowTabMethodImplement(get()) }
    viewModel { ProfileTabViewModel(get()) }

    single<FavoriteMethod> { FavoriteMethodImplement(get()) }
    viewModel { FavoriteViewModel(get()) }
}