package com.thor.githubuser3.Repository.User

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.thor.githubuser3.Data.Local.User.UserFavoriteScema
import io.reactivex.Flowable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    @SerializedName("avatar_url")
    val avatar: String = "",

    @SerializedName("company")
    val company: String? = "",

    @SerializedName("login")
    val username: String = "",

    @SerializedName("html_url")
    val htmlUrl: String = "",

    @SerializedName("location")
    val location: String? = "",

    @SerializedName("name")
    val name: String = "",

    @field:SerializedName("followers")
    val followers: Int = 0,

    @field:SerializedName("following")
    val following: Int = 0,

    @field:SerializedName("public_repos")
    val publicRepos: Int = 0,

    ) : Parcelable


fun User.toUserFavorite() = UserFavoriteScema(
    username = this.username,
    avatar = this.avatar,
    htmlUrl = this.htmlUrl,
)

fun List<UserFavoriteScema>.toListUser(): Flowable<List<User>> {
    return Flowable.just(this.map { item ->
        User(username = item.username, avatar = item.avatar, htmlUrl = item.htmlUrl)
    })
}