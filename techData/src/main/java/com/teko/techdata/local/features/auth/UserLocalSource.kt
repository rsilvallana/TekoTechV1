package com.teko.techdata.local.features.auth

import com.teko.techdata.remote.features.auth.domain.User
import io.reactivex.Completable
import io.reactivex.Single

interface UserLocalSource {

    fun saveUser(user: User): Single<User>

    fun getUser(): Single<User>

    fun deleteUser(): Completable
}
