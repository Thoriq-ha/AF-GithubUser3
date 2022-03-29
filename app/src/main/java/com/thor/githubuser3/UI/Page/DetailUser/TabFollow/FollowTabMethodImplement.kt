package com.thor.githubuser3.UI.Page.DetailUser.TabFollow

import com.thor.githubuser3.Repository.User.UserRepository

class FollowTabMethodImplement(private val repository: UserRepository) : FollowTabMethod {

    override fun follower(username: String) = repository.followers(username)

    override fun following(username: String) = repository.following(username)

}