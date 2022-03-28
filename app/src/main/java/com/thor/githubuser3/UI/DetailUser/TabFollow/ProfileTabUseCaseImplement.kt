package com.thor.githubuser3.UI.DetailUser.TabFollow

import com.thor.githubuser3.Repository.User.UserRepository

class ProfileTabUseCaseImplement(private val repository: UserRepository) : ProfileTabUseCase {

    override fun follower(username: String) = repository.followers(username)

    override fun following(username: String) = repository.following(username)

}