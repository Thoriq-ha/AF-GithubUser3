package com.thor.githubuser3.Data.Local.User

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        UserFavoriteScema::class
    ], version = 1, exportSchema = false
)
abstract class UserDatabase: RoomDatabase()  {
    abstract fun user(): UserDao
}