package com.thor.githubuser3.Data.Local.User

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(data: UserFavoriteScema): Completable

    @Query("SELECT * FROM ${UserLocalConfig.TABLE_FAVORITE}")
    fun findAll(): Flowable<List<UserFavoriteScema>>

    @Query("SELECT * FROM ${UserLocalConfig.TABLE_FAVORITE} WHERE username = :username LIMIT 1")
    fun find(username: String): Flowable<UserFavoriteScema>

    @Query("DELETE FROM ${UserLocalConfig.TABLE_FAVORITE} WHERE username = :username")
    fun delete(username: String): Completable

    @Query("DELETE FROM ${UserLocalConfig.TABLE_FAVORITE}")
    fun truncate(): Completable

}