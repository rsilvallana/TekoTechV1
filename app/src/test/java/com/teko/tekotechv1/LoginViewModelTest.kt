package com.teko.tekotechv1

import com.teko.data.features.auth.AuthRepository
import com.teko.domain.AccessToken
import com.teko.domain.User
import com.teko.tekotechv1.feature.login.LoginViewModel
import com.teko.tekotechv1.feature.login.LoginViewModelState
import io.mockk.*
import io.reactivex.Observer
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.DelicateCoroutinesApi
import org.junit.Before
import org.junit.Test

@DelicateCoroutinesApi
class LoginViewModelTest : BaseViewModelTest() {

    private lateinit var sut: LoginViewModel
    private val mockAuthRepository = mockk<AuthRepository>(relaxUnitFun = true)
    private val stateObserver = mockk<Observer<LoginViewModelState>>(relaxUnitFun = true)

    @Before
    fun setup() {
        sut = LoginViewModel(mockAuthRepository)
        sut.schedulers = schedulers
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
        val response = mockk<Pair<User, AccessToken>>()
        val stateSlots = mutableListOf<LoginViewModelState>()

        every {
            mockAuthRepository.login(any(), any(), any())
        } returns Single.just(response)

        every {
            stateObserver.onNext(capture(stateSlots))
        } just Runs

        sut.login("sample", "pass", false)
        testScheduler.triggerActions()

        verify(exactly = 3) {
            stateObserver.onNext(any())
        }

        assertEquals(LoginViewModelState.ShowLoading, stateSlots[0])
        assertEquals(LoginViewModelState.HideLoading, stateSlots[1])
        assertTrue(stateSlots[2] is LoginViewModelState.LoginSuccess)
    }
}
