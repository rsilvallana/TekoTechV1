package com.teko.tekotechv1.feature.login

import com.teko.common.base.BaseViewModel
import com.teko.techdata.repository.di.AuthRepository
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel() {

    private val _state by lazy {
        PublishSubject.create<LoginViewModelState>()
    }

    val state: Observable<LoginViewModelState> = _state

    fun login(email: String, password: String, isSpecial: Boolean) {
        authRepository.login(email, password, isSpecial)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .doOnSubscribe {
                _state.onNext(LoginViewModelState.ShowLoading)
            }
            .doOnError {
                _state.onNext(LoginViewModelState.HideLoading)
            }
            .doOnSuccess {
                _state.onNext(LoginViewModelState.HideLoading)
            }
            .subscribeBy(
                onSuccess = {
                    _state.onNext(LoginViewModelState.LoginSuccess(it))
                },
                onError = { throwable ->
                    _state.onNext(LoginViewModelState.ShowError(throwable))
                }
            )
            .addTo(disposables)
    }
}
