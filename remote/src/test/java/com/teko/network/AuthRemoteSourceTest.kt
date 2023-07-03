package com.teko.network

import com.teko.network.features.auth.AuthRemoteSource
import com.teko.network.features.auth.AuthRemoteSourceImpl
import com.teko.network.features.auth.models.LoginResponse
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class AuthRemoteSourceTest {

    private lateinit var sut: AuthRemoteSource
    private val mockApiService = mockk<TekoTechApiServices>()

    @Before
    fun setup() {
        sut = AuthRemoteSourceImpl(mockApiService)
    }

    @Test
    fun login_ShouldCallFromService() {
        val response = mockk<Response<LoginResponse>>()
        every {
            mockApiService.login(any())
        } returns
                Single.just(response)

        // Test the button in navigation view
        sut.login("test", "pass", false)

        verify { mockApiService.login(any()) }
    }
}
