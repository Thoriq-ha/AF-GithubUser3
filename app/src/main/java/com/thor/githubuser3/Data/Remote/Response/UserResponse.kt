package com.thor.githubuser3.Data.Remote.Response

import com.google.gson.annotations.SerializedName
import com.thor.githubuser3.Repository.User.User
import io.reactivex.Observable

data class UserSearchResponse(
    @SerializedName("total_count")
    val totalCount: Int,

    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,

    @SerializedName("items")
    val items: List<User> = emptyList()
)

fun UserSearchResponse.mapToList() = Observable.just(this.items)