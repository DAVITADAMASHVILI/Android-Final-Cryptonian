package com.example.cryptonian.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.cryptonian.R
import com.example.cryptonian.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        navigationController = findNavController(R.id.navHostFragment)
        setupActionBarWithNavController(navigationController, AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.converterFragment
            )
        ))
        binding.bottomNavigationView.setupWithNavController(navigationController)
    }
}