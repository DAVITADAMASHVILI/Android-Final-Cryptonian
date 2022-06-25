package com.example.cryptonian.activities

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.cryptonian.R
import com.example.cryptonian.broadcastReceivers.LowBatteryListener
import com.example.cryptonian.broadcastReceivers.LowBatteryReceiver
import com.example.cryptonian.databinding.ActivityMainBinding
import com.example.cryptonian.notifications.NotificationUtil

class MainActivity : AppCompatActivity(), LowBatteryListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        NotificationUtil.createNotificationChannel(this)
        registerAirplaneModeReceiver()

        navigationController = findNavController(R.id.navHostFragment)
        setupActionBarWithNavController(navigationController, AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.converterFragment
            )
        ))
        binding.bottomNavigationView.setupWithNavController(navigationController)
    }

    override fun onLowBattery() {
        NotificationUtil.showReminderNotification(this)
    }

    private fun registerAirplaneModeReceiver() {
        val lowBatteryReceiver = LowBatteryReceiver()
        lowBatteryReceiver.lowBatteryListener = this
        IntentFilter(Intent.ACTION_BATTERY_LOW).also {
            registerReceiver(lowBatteryReceiver, it)
        }
    }
}