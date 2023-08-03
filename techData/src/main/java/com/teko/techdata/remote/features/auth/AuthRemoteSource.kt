package com.teko.techdata.remote.features.auth

import com.teko.techdata.remote.features.auth.domain.AccessToken
import com.teko.techdata.remote.features.auth.domain.User
import io.reactivex.Single

interface AuthRemoteSource {

    fun login(
        email: String,
        password: String,
        isSpecial: Boolean
    ): Single<Pair<User, AccessToken>>

    fun checkSession(): Single<User>
}
