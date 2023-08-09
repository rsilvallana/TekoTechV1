package com.teko.tekotechv1.feature.splash

import android.util.Log
import com.teko.common.base.BaseViewModel
import com.teko.techdata.remote.features.auth.domain.User
import com.teko.techdata.repository.features.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel() {

    private val _state by lazy {
        PublishSubject.create<SplashState>()
    }
    val state: Observable<SplashState> = _state

    fun checkSession() {
        authRepository
            .checkSession()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                _state.onNext(SplashState.ShowLoading)
            }
            .doOnError {
                _state.onNext(SplashState.HideLoading)
            }
            .doOnSuccess {
                _state.onNext(SplashState.HideLoading)
            }
            .subscribeBy(
                onSuccess = {
                    if (it != User.empty()) {
                        _state.onNext(SplashState.UserLoggedIn)
                    } else {
                        _state.onNext(SplashState.UserNotLoggedIn)
                    }
                },
                onError = {
                    // TODO handle unauthorized exception in retrofit
                    Log.e(this::class.simpleName, it.stackTraceToString())
                }
            )
            .addTo(disposables)
    }
}
