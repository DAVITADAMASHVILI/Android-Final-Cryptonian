package com.example.cryptonian.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.cryptonian.R

object NotificationUtil {
    const val NOTIFICATION_ID = 1
    const val CHANNEL_ID = "Cryptonian"
    const val CHANNEL_NAME = "Reminders"
    const val DESCRIPTION = "Get notified every time your phone reaches low battery"

    fun showReminderNotification(context: Context) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat
            .Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_exchange)
            .setContentTitle("Convert Currency")
            .setContentText("Hurry up and calculate your preffered currency before you phone dies")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        manager.notify(NOTIFICATION_ID, notification.build())
    }

    fun createNotificationChannel(context: Context) {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance)
        channel.description = DESCRIPTION

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }
}