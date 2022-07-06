package com.ahmetbozkan.bgnmobi.ui.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.viewModels
import com.ahmetbozkan.bgnmobi.R
import com.ahmetbozkan.bgnmobi.base.BaseActivity
import com.ahmetbozkan.bgnmobi.databinding.ActivityLoginBinding
import com.ahmetbozkan.bgnmobi.ui.landing.LandingActivity
import com.ahmetbozkan.bgnmobi.util.extensions.canDrawOverlays
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    override val viewModel: LoginViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun initialize(savedInstanceState: Bundle?) {

        checkOverlayPermission()

        manageClick()

    }

    private fun checkOverlayPermission() {
        if (canDrawOverlays) launchActivity(LandingActivity(), true)
    }

    private fun manageClick() = with(binding) {
        btnGetOverlayPermission.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                startActivity(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        checkOverlayPermission()
    }

}