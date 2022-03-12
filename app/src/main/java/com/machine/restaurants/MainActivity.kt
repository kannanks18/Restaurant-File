package com.machine.restaurants

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.machine.restaurants.databinding.ActivityMainBinding
import com.machine.restaurants.utils.NetworkCheckUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var windowInsetsControllerCompat: WindowInsetsControllerCompat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        windowInsetsControllerCompat = WindowInsetsControllerCompat(window, window.decorView)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        val networkCheckUtil = NetworkCheckUtil(applicationContext)
        networkCheckUtil.observe(this) { b ->
            if (b) {
                if (navController.currentDestination?.id == R.id.internetFragment) {
                    navController.navigateUp()
                }
            } else {
                navController.navigate(MobileNavigationDirections.actionGlobalToInternetFragment())
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}