package com.teko.tekotechv1.feature.splash

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.teko.tekotechv1.MainActivity
import com.teko.tekotechv1.R
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModels()
    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initData()

        setupVmObservers()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

    private fun initData() {
        // TODO - verify version here
        // TODO - verify authentication status
        viewModel.checkSession()
    }

    private fun setupVmObservers() {
        viewModel
            .state
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onNext = ::handleState,
                onError = {
                    Log.e(this::class.java.simpleName, it.stackTraceToString())
                }
            )
            .addTo(disposables)
    }

    private fun handleState(state: SplashState) {
        Log.d(this::class.java.simpleName, "state: $state")
        when (state) {
            SplashState.HideLoading -> {
                // do nothing
            }
            SplashState.ShowLoading -> {
                // do nothing
            }
            SplashState.UserLoggedIn -> {
                navigateToHome()
            }
            SplashState.UserNotLoggedIn -> {
                navigateToAuth()
            }
        }
    }

    private fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(MainActivity.MAIN_NAVIGATION, MainActivity.START_HOME)
        intent.flags = FLAG_ACTIVITY_NO_HISTORY
        startActivity(intent)
    }

    private fun navigateToAuth() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(MainActivity.MAIN_NAVIGATION, MainActivity.START_LOGIN)
        intent.flags = FLAG_ACTIVITY_NO_HISTORY
        startActivity(intent)
    }
}
