package com.teko.tekotechv1.feature.splash

import com.teko.common.base.BaseViewModel
import com.teko.techdata.repository.features.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observer
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
    val state: Observer<SplashState> = _state

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
                onSuccess = {},
                onError = {}
            )
            .addTo(disposables)
    }
}
