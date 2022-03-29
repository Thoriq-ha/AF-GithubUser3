package com.thor.githubuser3.UI.Page.Home

import com.thor.githubuser3.Repository.User.User
import io.reactivex.Observable


interface HomeMethod {

    fun search(keyword: String): Observable<List<User>>

    fun list(): Observable<List<User>>

}
