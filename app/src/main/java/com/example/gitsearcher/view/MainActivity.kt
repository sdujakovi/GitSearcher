package com.example.gitsearcher.view

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.gitsearcher.R
import com.example.gitsearcher.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController)
        visibilityNavElements(navController)
        startToolbarAnimation(binding)

        binding.searchView.setOnQueryTextFocusChangeListener { v, hasFocus ->
            if(hasFocus){
                v.setBackgroundResource(R.drawable.asset_searchbar_second)
            }
            else{
                v.setBackgroundResource(R.drawable.asset_searchbar_first)
            }
        }
    }

    private fun startToolbarAnimation(binding: ActivityMainBinding) {
        val animationBar = (binding.toolbar.background as AnimationDrawable)
        animationBar.setEnterFadeDuration(100)
        animationBar.setExitFadeDuration(3400)
        animationBar.start()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


    private fun visibilityNavElements(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.itemFragment -> {
                    binding.textView.visibility = View.GONE
                    binding.searchView.visibility = View.GONE
                    binding.toolbar.setNavigationIcon(R.drawable.ic_back_custom)
                }
                else -> {
                    binding.textView.visibility = View.VISIBLE
                    binding.searchView.visibility = View.VISIBLE
                }
            }
        }
    }
}

