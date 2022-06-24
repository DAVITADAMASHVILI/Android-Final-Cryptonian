package com.example.cryptonian.room

import androidx.room.*
import com.example.cryptonian.api.CoinDao
import com.example.cryptonian.api.dto.Coin

@Database(entities = [Coin::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getCoinDao(): CoinDao
}