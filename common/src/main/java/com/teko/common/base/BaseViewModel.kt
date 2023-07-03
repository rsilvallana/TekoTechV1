package com.teko.common.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    open val disposables: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    @Inject
    lateinit var schedulers: BaseSchedulerProvider

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
