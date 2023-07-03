package com.teko.local

import android.content.SharedPreferences
import com.teko.domain.AccessToken
import com.teko.domain.Session
import com.teko.domain.User
import com.teko.local.features.session.SessionLocalSource
import com.teko.local.features.session.SessionLocalSourceImpl
import com.teko.local.features.token.AccessTokenLocalSource
import com.teko.local.features.user.UserLocalSource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class SessionLocalSourceTest {

    private lateinit var sut: SessionLocalSource
    private val mockUserLocalSource = mockk<UserLocalSource>(relaxed = true, relaxUnitFun = true)
    private val mockAccessTokenLocalSource = mockk<AccessTokenLocalSource>(relaxed = true, relaxUnitFun = true)
    private val mockSharedPreferences = mockk<SharedPreferences>(relaxed = true, relaxUnitFun = true)

    @Before
    fun setup() {
        sut = SessionLocalSourceImpl(mockUserLocalSource, mockAccessTokenLocalSource, mockSharedPreferences)
    }

    @Test
    fun getSession_ShouldCallFromDb() {
        val user = mockk<User>()
        val accessToken = mockk<AccessToken>()
        every {
            mockUserLocalSource.getUser()
        } returns Single.just(user)

        every {
            mockAccessTokenLocalSource.getAccessToken()
        } returns Single.just(accessToken)

        sut.getSession()

        verify {
            mockUserLocalSource.getUser()
            mockAccessTokenLocalSource.getAccessToken()
        }
    }

    @Test
    fun saveSession_ShouldCallFromDependencies() {
        val session = mockk<Session>(relaxed = true)

        every {
            mockUserLocalSource.saveUser(any())
        } returns Single.just(session.user)

        every {
            mockAccessTokenLocalSource.saveAccessToken(any())
        } returns Single.just(session.accessToken)

        sut.saveSession(session)
            .test()
            .assertComplete()

        verify {
            mockUserLocalSource.saveUser(any())
            mockAccessTokenLocalSource.saveAccessToken(any())
        }
    }

    @Test
    fun getUserToken_ShouldCallFromPrefs() {
        every {
            sut.getUserToken()
        } returns "sampleToken"

        sut.getUserToken()

        verify {
            mockAccessTokenLocalSource
                .getAccessTokenFromPrefs()
        }
    }

    @Test
    fun clearSession_ShouldCallDeleteUser() {
        every {
            sut.clearSession()
        } returns Completable.complete()

        sut.clearSession()

        verify {
            mockUserLocalSource.deleteUser()
        }
    }

    @Test
    fun clearSession_ShouldCallDeleteToken() {
        every {
            sut.clearSession()
        } returns Completable.complete()

        sut.clearSession()

        verify {
            mockAccessTokenLocalSource.deleteToken()
        }
    }
}
