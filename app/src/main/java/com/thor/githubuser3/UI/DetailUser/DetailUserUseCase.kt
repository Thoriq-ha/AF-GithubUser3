package com.thor.githubuser3.UI.DetailUser

import com.thor.githubuser3.Data.Local.User.UserFavoriteScema
import com.thor.githubuser3.Repository.User.User
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

interface DetailUserUseCase {
    fun detail(username: String): Observable<User>
    fun favorite(user: User): Completable
    fun find(username: String): Flowable<UserFavoriteScema>
    fun delete(username: String): Completable
}