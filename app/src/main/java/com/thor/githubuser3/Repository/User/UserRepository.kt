package com.thor.githubuser3.Repository.User

import com.thor.githubuser3.Data.API.Services.UserServices
import com.thor.githubuser3.Data.Local.UserRoom.UserDao

class UserRepository(
    private val dao: UserDao,
    private val service: UserServices
) {

    // https://api.github.com/users
    fun list() = service.users()

    // https://api.github.com/users/thoriq-ha/followers
    fun followers(username: String) = service.followers(username)

    // https://api.github.com/users/thoriq-ha/following
    fun following(username: String) = service.following(username)

    // https://api.github.com/search/users?q=keyword
    fun search(keyword: String) = service.search(keyword)

    // https://api.github.com/users/username
    fun detail(username: String) = service.user(username)

    fun favorite(user: User) = dao.save(user.toUserFavorite())

    fun favorite(username: String) = dao.find(username)

    fun favorites() = dao.findAll()

    fun deleteFromFavorite(username: String) = dao.delete(username)

    fun truncateFavorite() = dao.truncate()
}