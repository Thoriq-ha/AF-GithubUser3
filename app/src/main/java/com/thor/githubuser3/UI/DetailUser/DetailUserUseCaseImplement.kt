package com.thor.githubuser3.UI.DetailUser

import com.thor.githubuser3.Repository.User.User
import com.thor.githubuser3.Repository.User.UserRepository

class DetailUserUseCaseImplement(private val repository: UserRepository): DetailUserUseCase {

    override fun detail(username: String) = repository.detail(username)

    override fun favorite(user: User) = repository.favorite(user)

    override fun find(username: String) = repository.favorite(username)

    override fun delete(username: String) = repository.deleteFromFavorite(username)
}