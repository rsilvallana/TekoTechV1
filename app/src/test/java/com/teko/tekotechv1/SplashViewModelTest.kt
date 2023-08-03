package com.teko.tekotechv1

import com.teko.techdata.remote.features.auth.domain.User
import com.teko.techdata.repository.features.auth.AuthRepository
import com.teko.tekotechv1.base.RxImmediateSchedulerRule
import com.teko.tekotechv1.feature.splash.SplashViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.ClassRule
import org.junit.Test

class SplashViewModelTest {

    private lateinit var sut: SplashViewModel
    private val authRepository = mockk<AuthRepository>()

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Before
    fun setup() {
        sut = SplashViewModel(authRepository)
    }

    @Test
    fun checkSession_ShouldCalLFromAuthRepository() {
        val responseUser = mockk<User>()
        every {
            authRepository.checkSession()
        } returns
            Single.just(responseUser)

        sut.checkSession()

        verify {
            authRepository.checkSession()
        }
    }
}