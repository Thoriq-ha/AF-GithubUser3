package com.thor.githubuser3.Data.Local.UserRoom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = UserLocalConfig.TABLE_FAVORITE)
data class UserFavoriteSchema(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "username") var username: String,
    @ColumnInfo(name = "html_url") val htmlUrl: String,
    @ColumnInfo(name = "avatar") val avatar: String,
)