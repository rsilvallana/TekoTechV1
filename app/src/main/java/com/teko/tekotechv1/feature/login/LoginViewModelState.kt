package com.teko.tekotechv1.feature.login

import com.teko.techdata.remote.features.auth.domain.AccessToken
import com.teko.techdata.remote.features.auth.domain.User

sealed class LoginViewModelState {

    object ShowLoading : LoginViewModelState()

    object HideLoading : LoginViewModelState()

    class ShowError(val throwable: Throwable) : LoginViewModelState()

    class LoginSuccess(val result: Pair<User, AccessToken>) : LoginViewModelState()
}
