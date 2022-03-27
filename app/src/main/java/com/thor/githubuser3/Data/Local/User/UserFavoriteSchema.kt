package com.thor.githubuser3.Data.Local.User

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_favorite")
data class UserFavoriteScema (
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "username") var username: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "avatar") val avatar: String,
)