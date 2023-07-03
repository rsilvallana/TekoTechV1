package com.teko.local.features.token

import com.teko.domain.AccessToken
import io.reactivex.Completable
import io.reactivex.Single

interface AccessTokenLocalSource {

    fun getAccessToken(): Single<AccessToken>

    fun getAccessTokenFromPrefs(): String

    fun saveAccessToken(accessToken: AccessToken): Single<AccessToken>

    fun deleteToken(): Completable
}
