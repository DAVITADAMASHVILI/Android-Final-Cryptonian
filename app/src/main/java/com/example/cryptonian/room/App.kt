package com.example.cryptonian.room

import android.app.Application
import androidx.room.Room

class App: Application() {
    companion object {
        lateinit var instance: App
            private set
    }

    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()

        instance = this
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "USERS"
        ).allowMainThreadQueries().build()
    }
}