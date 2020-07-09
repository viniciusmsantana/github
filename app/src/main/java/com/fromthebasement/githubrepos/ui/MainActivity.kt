package com.fromthebasement.githubrepos.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.fromthebasement.githubrepos.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * Single Activity which holds all application's fragments
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val navController by lazy { Navigation.findNavController(this, R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolbarController()
    }

    /**
     * Defines application toolbar title based on the current fragment
     */
    private fun setToolbarController() {
        navController.addOnDestinationChangedListener { _, destination, arguments ->
            title = when (destination.id) {
                R.id.repo_details -> {
                    (arguments?.getString("repoName")) ?: getString(R.string.app_title_default)
                }
                else -> {
                    getString(R.string.app_title_default)
                }
            }
        }
    }
}