package com.teko.techdata.local.features.auth.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.teko.commondata.local.BaseDao
import com.teko.techdata.local.features.auth.domain.AccessTokenDB
import io.reactivex.Completable
import io.reactivex.Single

@Dao
abstract class TokenDao : BaseDao<AccessTokenDB> {

    @Query("SELECT * FROM ${AccessTokenDB.TOKEN_TABLE_NAME} LIMIT 1")
    abstract fun getToken(): Single<AccessTokenDB>

    @Query("DELETE FROM ${AccessTokenDB.TOKEN_TABLE_NAME}")
    abstract fun logoutToken(): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveToken(tokenDb: AccessTokenDB): Single<Long>
}
