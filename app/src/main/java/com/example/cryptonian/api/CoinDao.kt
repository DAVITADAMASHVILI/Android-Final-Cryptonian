package com.example.cryptonian.api

import androidx.room.Dao
import androidx.room.*
import com.example.cryptonian.api.dto.Coin

@Dao
interface CoinDao {
    @Query("SELECT * FROM COINS")
    fun getAllCoins(): List<Coin>

    @Insert
    fun insert(vararg coins: Coin)

    @Delete
    fun deleteCoin(user: Coin)

    @Query("DELETE FROM COINS")
    fun deleteAll()
}