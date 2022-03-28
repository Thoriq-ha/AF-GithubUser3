package com.thor.githubuser3.UI.DetailUser.TabFollow

import com.thor.githubuser3.Repository.User.User
import io.reactivex.Observable

interface ProfileTabUseCase {
    fun follower(username: String): Observable<List<User>>
    fun following(username: String): Observable<List<User>>
}