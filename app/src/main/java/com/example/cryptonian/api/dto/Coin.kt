package com.example.cryptonian.api.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "COINS")
data class Coin(
    @PrimaryKey
    val id: String,
    val symbol: String?,
    val image: String?,
    @SerializedName("current_price")
    val price: Double?,
    @SerializedName("market_cap_rank")
    val rank: Int?,
    @SerializedName("high_24h")
    val high: Double?,
    @SerializedName("low_24h")
    val low: Double?
)