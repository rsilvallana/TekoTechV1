package com.teko.tekotechv1.feature.splash

sealed class SplashState {

    object ShowLoading : SplashState()

    object HideLoading : SplashState()

    object UserLoggedIn : SplashState()

    object UserNotLoggedIn : SplashState()
}
