package com.teko.tekotechv1.feature.splash

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.teko.tekotechv1.MainActivity
import com.teko.tekotechv1.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initData()

        navigateToMain()
    }

    private fun initData() {
        // TODO - verify version here
        // TODO - verify authentication status
        viewModel.checkSession()
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = FLAG_ACTIVITY_NO_HISTORY
        startActivity(intent)
    }
}
