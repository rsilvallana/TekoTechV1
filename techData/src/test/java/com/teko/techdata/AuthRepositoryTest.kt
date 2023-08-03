package com.teko.techdata

import com.teko.techdata.remote.features.auth.AuthRemoteSource
import com.teko.techdata.remote.features.auth.domain.AccessToken
import com.teko.techdata.remote.features.auth.domain.User
import com.teko.techdata.repository.features.auth.AuthRepository
import com.teko.techdata.repository.features.auth.AuthRepositoryImpl
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class AuthRepositoryTest {

    private lateinit var sut: AuthRepository
    private val authRemoteSource = mockk<AuthRemoteSource>()

    @Before
    fun setup() {
        sut = AuthRepositoryImpl(authRemoteSource)
    }

    @Test
    fun login_ShouldCallFromRemoteSource() {
        val responseUser = mockk<User>()
        val responseAccessToken = mockk<AccessToken>()
        every {
            authRemoteSource.login(any(), any(), any())
        } returns
            Single.just(Pair(responseUser, responseAccessToken))

        sut.login("email", "password", false)

        verify { authRemoteSource.login(any(), any(), any()) }
    }

    @Test
    fun checkSession_ShouldCallFromRemoteSource() {
        val responseUser = mockk<User>()
        every {
            authRemoteSource.checkSession()
        } returns
            Single.just(responseUser)

        sut.checkSession()

        verify {
            authRemoteSource.checkSession()
        }
    }
}