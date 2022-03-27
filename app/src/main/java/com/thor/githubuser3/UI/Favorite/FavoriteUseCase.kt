package com.thor.githubuser3.UI.Favorite

import com.thor.githubuser3.Repository.User.User
import io.reactivex.Completable
import io.reactivex.Flowable


interface FavoriteUseCase {
    fun list(): Flowable<List<User>>

    fun truncate(): Completable
}