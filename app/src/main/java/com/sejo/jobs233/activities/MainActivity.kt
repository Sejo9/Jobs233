package com.sejo.jobs233.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.sejo.jobs233.R
import com.sejo.jobs233.network.API
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavDestinations = setOf(
            R.id.dashboardFragment,
            R.id.projectsFragment,
            R.id.inboxFragment,
            R.id.profileFragment,
            R.id.settingsFragment
        )
        val appBarConfiguration = AppBarConfiguration(bottomNavDestinations)

        bottom_nav.setupWithNavController(navController)
        toolbar.setupWithNavController(navController, appBarConfiguration)

        API.APIContext = this.applicationContext
    }

}