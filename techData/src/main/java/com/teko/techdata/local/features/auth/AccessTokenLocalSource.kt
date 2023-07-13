package com.teko.techdata.local.features.auth

import com.teko.techdata.remote.features.auth.domain.AccessToken
import io.reactivex.Completable
import io.reactivex.Single

interface AccessTokenLocalSource {

    fun getAccessToken(): Single<AccessToken>

    fun getAccessTokenFromPrefs(): String

    fun saveAccessToken(accessToken: AccessToken): Single<AccessToken>

    fun deleteToken(): Completable
}
