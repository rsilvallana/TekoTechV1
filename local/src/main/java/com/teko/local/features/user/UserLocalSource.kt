package com.teko.local.features.user

import com.teko.domain.User
import io.reactivex.Completable
import io.reactivex.Single

interface UserLocalSource {

    fun saveUser(user: User): Single<User>

    fun getUser(): Single<User>

    fun deleteUser(): Completable
}
