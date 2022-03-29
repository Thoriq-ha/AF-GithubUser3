package com.thor.githubuser3.UI.Page.DetailUser

import com.thor.githubuser3.Data.Local.UserRoom.UserFavoriteSchema
import com.thor.githubuser3.Repository.User.User
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

interface DetailUserMethod {
    fun detail(username: String): Observable<User>
    fun favorite(user: User): Completable
    fun find(username: String): Flowable<UserFavoriteSchema>
    fun delete(username: String): Completable
}