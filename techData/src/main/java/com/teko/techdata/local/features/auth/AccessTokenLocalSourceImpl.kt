package com.teko.techdata.local.features.auth

import android.content.SharedPreferences
import androidx.room.EmptyResultSetException
import com.teko.commondata.local.OnErrorResumeNext
import com.teko.techdata.remote.features.auth.domain.AccessToken
import com.teko.techdata.local.features.auth.dao.TokenDao
import com.teko.techdata.local.features.auth.domain.AccessTokenDB
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class AccessTokenLocalSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val tokenDao: TokenDao
) : AccessTokenLocalSource {

    companion object {
        const val PREF_USER_TOKEN = "PREF_USER_TOKEN"
    }

    override fun getAccessToken(): Single<AccessToken> {
        return tokenDao
            .getToken()
            .compose(
                OnErrorResumeNext<AccessTokenDB, EmptyResultSetException>(
                    AccessTokenDB(),
                    EmptyResultSetException::class.java
                )
            )
            .map {
                AccessTokenDB.toDomain(it)
            }
    }

    override fun getAccessTokenFromPrefs(): String {
        return sharedPreferences.getString(PREF_USER_TOKEN, "") ?: ""
    }

    override fun saveAccessToken(accessToken: AccessToken): Single<AccessToken> {
        return tokenDao
            .logoutToken()
            .andThen(tokenDao.saveToken(AccessTokenDB.fromDomain(accessToken)))
            .map {
                saveAccessTokenToPref(accessToken.authCookie)
            }
            .map { accessToken }
    }

    private fun saveAccessTokenToPref(accessToken: String) {
        sharedPreferences
            .edit()
            .apply {
                putString(PREF_USER_TOKEN, accessToken)
                apply()
            }
    }

    override fun deleteToken(): Completable {
        saveAccessTokenToPref("")
        return tokenDao.logoutToken()
    }
}
