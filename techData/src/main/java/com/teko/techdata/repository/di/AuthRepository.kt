package com.teko.techdata.repository.di

import com.teko.techdata.remote.features.auth.domain.AccessToken
import com.teko.techdata.remote.features.auth.domain.User
import io.reactivex.Single

interface AuthRepository {

    fun login(
        email: String,
        password: String,
        isSpecial: Boolean
    ): Single<Pair<User, AccessToken>>
}
