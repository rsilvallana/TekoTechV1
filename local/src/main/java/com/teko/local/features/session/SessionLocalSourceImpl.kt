package com.teko.local.features.session

import android.content.SharedPreferences
import com.teko.domain.Session
import com.teko.local.features.token.AccessTokenLocalSource
import com.teko.local.features.user.UserLocalSource
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class SessionLocalSourceImpl @Inject constructor(
    private val userLocalSource: UserLocalSource,
    private val accessTokenLocalSource: AccessTokenLocalSource,
    private val sharedPreferences: SharedPreferences
) : SessionLocalSource {

    var session: Session? = null

    override fun getSession(): Single<Session> {
        if (session == null) {
            return getSessionFromDb()
                .doOnSuccess { this.session = it }
        }

        return Single.just(session)
    }

    override fun saveSession(session: Session): Single<Session> {
        return Single
            .zip(
                userLocalSource.saveUser(session.user),
                accessTokenLocalSource.saveAccessToken(session.accessToken)
            ) { user, accessToken ->
                Pair(user, accessToken)
            }
            .map { pair ->
                session.user = pair.first
                session.accessToken = pair.second

                this.session = session
                this.session
            }
    }

    override fun getUserToken(): String {
        return accessTokenLocalSource.getAccessTokenFromPrefs()
    }

    override fun clearSession(): Completable {
        return userLocalSource
            .deleteUser()
            .andThen(accessTokenLocalSource.deleteToken())
            .doOnComplete {
                this.session = null
            }
    }

    private fun getSessionFromDb(): Single<Session> {
        return Single
            .zip(
                userLocalSource.getUser(),
                accessTokenLocalSource.getAccessToken()
            ) { user, accessToken ->
                val session = Session(user, accessToken)

                session
            }
    }
}
