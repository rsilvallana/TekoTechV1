package com.teko.tekotechv1.feature.login

import android.os.Bundle
import android.view.View
import com.teko.common.base.BaseFragment
import com.teko.tekotechv1.MainActivity
import com.teko.tekotechv1.R
import com.teko.tekotechv1.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_login

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {
        (activity as MainActivity).hideStatusBar()
    }
}
