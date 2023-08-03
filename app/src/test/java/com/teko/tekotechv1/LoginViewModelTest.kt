package com.teko.tekotechv1

import com.teko.techdata.remote.features.auth.domain.AccessToken
import com.teko.techdata.remote.features.auth.domain.User
import com.teko.techdata.repository.features.auth.AuthRepository
import com.teko.tekotechv1.base.RxImmediateSchedulerRule
import com.teko.tekotechv1.feature.login.LoginViewModel
import com.teko.tekotechv1.feature.login.LoginViewModelState
import io.mockk.*
import io.reactivex.Observer
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.DelicateCoroutinesApi
import org.junit.Before
import org.junit.ClassRule
import org.junit.Test

@DelicateCoroutinesApi
class LoginViewModelTest {

    private lateinit var sut: LoginViewModel
    private val mockAuthRepository = mockk<AuthRepository>(relaxUnitFun = true)
    private val stateObserver = mockk<Observer<LoginViewModelState>>(relaxUnitFun = true)

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Before
    fun setup() {
        sut = LoginViewModel(mockAuthRepository)
        sut.state.subscribe(stateObserver)
    }

    @Test
    fun login_shouldCallFromAuthRepository() {
        val response = mockk<Pair<User, AccessToken>>()
        every {
            mockAuthRepository.login(any(), any(), any())
        } returns Single.just(response)

        sut.login("sample", "pass", false)

        verify {
            mockAuthRepository.login(any(), any(), any())
        }
    }

    @Test
    fun loginSuccess_ShouldEmitLoginState() {
        val responseUser = mockk<User>()
        val responseAccessToken = mockk<AccessToken>()
        val stateSlots = mutableListOf<LoginViewModelState>()

        every {
            mockAuthRepository.login(any(), any(), any())
        } returns Single.just(Pair(responseUser, responseAccessToken))

        every {
            stateObserver.onNext(capture(stateSlots))
        } just Runs

        sut.login("sample", "pass", false)

        verify(exactly = 3) {
            stateObserver.onNext(any())
        }

        assertEquals(LoginViewModelState.ShowLoading, stateSlots[0])
        assertEquals(LoginViewModelState.HideLoading, stateSlots[1])
        assertTrue(stateSlots[2] is LoginViewModelState.LoginSuccess)
    }
}
