package com.thor.githubuser3.Data.Local.UserRoom

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        UserFavoriteSchema::class
    ], version = 1, exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {
    abstract fun user(): UserDao
}