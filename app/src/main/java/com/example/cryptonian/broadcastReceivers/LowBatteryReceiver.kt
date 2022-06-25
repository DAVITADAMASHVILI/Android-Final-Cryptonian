package com.example.cryptonian.broadcastReceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class LowBatteryReceiver: BroadcastReceiver(){
    var lowBatteryListener: LowBatteryListener? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        lowBatteryListener?.onLowBattery()
    }
}

interface LowBatteryListener {
    fun onLowBattery()
}