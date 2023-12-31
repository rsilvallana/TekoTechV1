package com.teko.local.features.user.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.teko.local.base.BaseDao
import com.teko.local.features.user.model.UserDB
import io.reactivex.Completable
import io.reactivex.Single

@Dao
abstract class UserDao : BaseDao<UserDB> {

    @Query("SELECT * FROM ${UserDB.USER_TABLE_NAME} LIMIT 1")
    abstract fun getUser(): Single<UserDB>

    @Query("DELETE FROM ${UserDB.USER_TABLE_NAME}")
    abstract fun deleteUsers(): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveUser(dbUser: UserDB): Single<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updateUser(dbUser: UserDB): Completable
}
