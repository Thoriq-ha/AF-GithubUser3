package com.thor.githubuser3.UI.Page.DetailUser

import com.thor.githubuser3.Repository.User.User
import com.thor.githubuser3.Repository.User.UserRepository

class DetailUserMethodImplement(private val repository: UserRepository) : DetailUserMethod {
    override fun detail(username: String) = repository.detail(username)
    override fun favorite(user: User) = repository.favorite(user)
    override fun find(username: String) = repository.favorite(username)
    override fun delete(username: String) = repository.deleteFromFavorite(username)
}