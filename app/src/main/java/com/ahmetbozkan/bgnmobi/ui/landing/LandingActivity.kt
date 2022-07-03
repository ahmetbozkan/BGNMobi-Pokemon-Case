package com.ahmetbozkan.bgnmobi.ui.landing

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ahmetbozkan.bgnmobi.R
import com.ahmetbozkan.bgnmobi.base.BaseActivity
import com.ahmetbozkan.bgnmobi.databinding.ActivityLandingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LandingActivity : BaseActivity<ActivityLandingBinding, LandingViewModel>() {

    override val viewModel: LandingViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.activity_landing

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun initialize(savedInstanceState: Bundle?) {

        initNavigation()

    }

    private fun initNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.findNavController()

        initDrawer()
    }

    private fun initDrawer() {
        appBarConfiguration = AppBarConfiguration(
            navController.graph,
            binding.drawerLayout
        )

        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)

        with(binding.navigationView) {
            setupWithNavController(navController)
            menu.findItem(R.id.menu_close_app).setOnMenuItemClickListener {
                finish()
                true
            }
        }

    }
}