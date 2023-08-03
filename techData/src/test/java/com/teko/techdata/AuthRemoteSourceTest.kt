package com.teko.techdata

import com.teko.techdata.remote.TechApiServices
import com.teko.techdata.remote.features.auth.AuthRemoteSource
import com.teko.techdata.remote.features.auth.AuthRemoteSourceImpl
import com.teko.techdata.remote.features.auth.dto.LoginResponse
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class AuthRemoteSourceTest {

    private lateinit var sut: AuthRemoteSource
    private val api = mockk<TechApiServices>()


    @Before
    fun setup() {
        sut = AuthRemoteSourceImpl(api)
    }

    @Test
    fun login_ShouldCallFromService() {
        val response = mockk<Response<LoginResponse>>()
        every {
            api.login(any())
        } returns
            Single.just(response)

        sut.login("email", "pass", false)

        verify { api.login(any()) }
    }

    @Test
    fun checkSession_ShouldCallFromService() {
        val response = mockk<LoginResponse>()
        every {
            api.checkSession()
        } returns
            Single.just(response)

        sut.checkSession()

        verify { api.checkSession() }
    }
}