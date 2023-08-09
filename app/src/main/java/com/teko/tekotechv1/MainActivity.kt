package com.teko.tekotechv1

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.navigation.findNavController
import com.teko.common.base.BaseActivity
import com.teko.tekotechv1.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handleExtras()
    }

    private fun handleExtras() {
        intent?.extras?.let {
            when (it.getString(MAIN_NAVIGATION)) {
                START_HOME -> {}
                START_LOGIN -> {
                    findNavController(R.id.nav_host_fragment)
                        .navigate(MainGraphDirections.actionGlobalToAuthGraph())
                }
            }
        }
    }

    @Suppress("DEPRECATION")
    fun hideStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        window.apply {
            decorView
                .systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

            statusBarColor = Color.TRANSPARENT
        }
    }

    companion object {
        const val MAIN_NAVIGATION = "main_navigation"

        const val START_LOGIN = "start_login"
        const val START_HOME = "start_home"
    }
}
