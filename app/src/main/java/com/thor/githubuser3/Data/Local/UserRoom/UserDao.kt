package com.thor.githubuser3.Data.Local.UserRoom

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(data: UserFavoriteSchema): Completable

    @Query("SELECT * FROM ${UserLocalConfig.TABLE_FAVORITE}")
    fun findAll(): Flowable<List<UserFavoriteSchema>>

    @Query("SELECT * FROM ${UserLocalConfig.TABLE_FAVORITE} WHERE username = :username LIMIT 1")
    fun find(username: String): Flowable<UserFavoriteSchema>

    @Query("DELETE FROM ${UserLocalConfig.TABLE_FAVORITE} WHERE username = :username")
    fun delete(username: String): Completable

    @Query("DELETE FROM ${UserLocalConfig.TABLE_FAVORITE}")
    fun truncate(): Completable

}