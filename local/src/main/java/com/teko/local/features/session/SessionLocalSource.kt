package com.teko.local.features.session

import com.teko.domain.Session
import io.reactivex.Completable
import io.reactivex.Single

interface SessionLocalSource {

    fun getSession(): Single<Session>

    fun saveSession(session: Session): Single<Session>

    fun getUserToken(): String

    fun clearSession(): Completable
}
