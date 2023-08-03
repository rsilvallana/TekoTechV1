package com.teko.techdata.repository.features.auth

import com.teko.techdata.remote.features.auth.AuthRemoteSource
import com.teko.techdata.remote.features.auth.domain.AccessToken
import com.teko.techdata.remote.features.auth.domain.User
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

    override fun checkSession(): Single<User> {
        return authRemoteSource
            .checkSession()
    }
}
