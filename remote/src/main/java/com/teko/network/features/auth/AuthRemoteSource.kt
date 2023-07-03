package com.teko.network.features.auth

import com.teko.domain.AccessToken
import com.teko.domain.User
import io.reactivex.Single

interface AuthRemoteSource {

    fun login(
        email: String,
        password: String,
        isSpecial: Boolean
    ): Single<Pair<User, AccessToken>>
}
