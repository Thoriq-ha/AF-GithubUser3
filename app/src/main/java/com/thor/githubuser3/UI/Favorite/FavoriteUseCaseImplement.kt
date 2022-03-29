package com.thor.githubuser3.UI.Favorite

import com.thor.githubuser3.Repository.User.User
import com.thor.githubuser3.Repository.User.UserRepository
import com.thor.githubuser3.Repository.User.toListUser
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class FavoriteUseCaseImplement(val repository: UserRepository) : FavoriteUseCase {
    override fun list(): Flowable<List<User>> = repository.favorites()
        .subscribeOn(Schedulers.io())
        .flatMap { database -> database.toListUser() }

    override fun truncate() = repository.truncateFavorite()
}