package com.ahmetbozkan.bgnmobi.ui.login

import android.os.Bundle
import androidx.activity.viewModels
import com.ahmetbozkan.bgnmobi.R
import com.ahmetbozkan.bgnmobi.base.BaseActivity
import com.ahmetbozkan.bgnmobi.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    override val viewModel: LoginViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun initialize(savedInstanceState: Bundle?) {

    }


}