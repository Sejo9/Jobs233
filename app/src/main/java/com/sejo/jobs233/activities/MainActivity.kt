package com.sejo.jobs233.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.sejo.jobs233.R
import com.sejo.jobs233.database.JobsDatabase
import com.sejo.jobs233.databinding.ActivityMainBinding
import com.sejo.jobs233.network.API
import com.sejo.jobs233.network.checkNetworkConnection
import com.sejo.jobs233.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbar)
        title = "Dashboard"

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavDestinations = setOf(
            R.id.dashboardFragment,
            R.id.projectsFragment,
            R.id.walletFragment,
            R.id.inboxFragment,
            R.id.profileFragment
        )
        val appBarConfiguration = AppBarConfiguration(bottomNavDestinations)

        binding.bottomNav.setupWithNavController(navController)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        API.APIContext = this.applicationContext



        if (checkNetworkConnection(this)) {
            val database = JobsDatabase.getInstance(this)
            lifecycleScope.launch(Dispatchers.IO) {
                UserRepository(database, this@MainActivity).setUserDetails()
            }

        }
    }

}