package com.teko.local.features.token.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.teko.domain.AccessToken

@Entity(tableName = AccessTokenDB.TOKEN_TABLE_NAME)
data class AccessTokenDB(
    @PrimaryKey
    var authCookie: String = ""
) {
    companion object {
        const val TOKEN_TABLE_NAME = "token"

        fun toDomain(accessTokenDB: AccessTokenDB): AccessToken {
            return with(accessTokenDB) {
                AccessToken(
                    authCookie = authCookie
                )
            }
        }

        fun fromDomain(accessToken: AccessToken): AccessTokenDB {
            return with(accessToken) {
                AccessTokenDB(
                    authCookie
                )
            }
        }
    }

    constructor() : this("")

    val bearerToken: String get() = "Bearer $authCookie"
}
