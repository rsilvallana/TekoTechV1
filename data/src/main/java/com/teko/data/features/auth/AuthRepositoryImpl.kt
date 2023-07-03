package com.teko.data.features.auth

import com.teko.domain.AccessToken
import com.teko.domain.User
import com.teko.network.features.auth.AuthRemoteSource
import io.reactivex.Single
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteSource: AuthRemoteSource
) : AuthRepository {

    override fun login(
        email: String,
        password: String,
        isSpecial: Boolean
    ): Single<Pair<User, AccessToken>> {
        // TODO usage of local
        return authRemoteSource.login(email, password, isSpecial)
    }
}
