package com.thor.githubuser3.UI.Page.DetailUser.TabFollow

import com.thor.githubuser3.Repository.User.User
import io.reactivex.Observable

interface FollowTabMethod {
    fun follower(username: String): Observable<List<User>>
    fun following(username: String): Observable<List<User>>
}