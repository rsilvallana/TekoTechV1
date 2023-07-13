package com.teko.techdata.local.features.auth

import com.teko.techdata.remote.features.auth.domain.Session
import io.reactivex.Completable
import io.reactivex.Single

interface SessionLocalSource {

    fun getSession(): Single<Session>

    fun saveSession(session: Session): Single<Session>

    fun getUserToken(): String

    fun clearSession(): Completable
}
