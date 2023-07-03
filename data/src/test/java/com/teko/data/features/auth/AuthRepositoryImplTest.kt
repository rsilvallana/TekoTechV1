package com.teko.data.features.auth

import com.teko.domain.AccessToken
import com.teko.domain.User
import com.teko.network.features.auth.AuthRemoteSource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import junit.framework.Assert
import org.junit.Before
import org.junit.Test

class AuthRepositoryImplTest {

    private lateinit var sut: AuthRepository
    private val mockAuthRemoteSource = mockk<AuthRemoteSource>(relaxed = true, relaxUnitFun = true)

    @Before
    fun setup() {
        sut = AuthRepositoryImpl(mockAuthRemoteSource)
    }

    @Test
    fun login_ShouldCallFromRemoteSource() {
        val response = mockk<Pair<User, AccessToken>>(relaxed = true)
        every {
            sut
                .login(any(), any(), any())
        } returns Single.just(response)

        sut
            .login("email", "pass", false)

        verify {
            mockAuthRemoteSource
                .login(any(), any(), any())
        }
    }

    @Test
    fun login_ShouldReceiveDataFromRemoteSource() {

        val response = mockk<Pair<User, AccessToken>>(relaxed = true)
        every {
            sut
                .login(any(), any(), any())
        } returns Single.just(response)

        val result = sut
            .login("email", "pass", false)
            .blockingGet()

        Assert.assertEquals(
            result,
            response
        )
    }
}
