package com.thor.githubuser3.UI.Page.Home

import com.thor.githubuser3.Data.API.Response.mapToList
import com.thor.githubuser3.Repository.User.User
import com.thor.githubuser3.Repository.User.UserRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class HomeMethodImplement(private val repository: UserRepository) : HomeMethod {

    override fun search(keyword: String): Observable<List<User>> {
        return repository.search(keyword)
            .subscribeOn(Schedulers.io())
            .flatMap { response -> response.mapToList() }
    }

    override fun list(): Observable<List<User>> = repository.list()

}