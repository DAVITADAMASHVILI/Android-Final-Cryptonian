package com.example.cryptonian.api

import com.example.cryptonian.api.dto.Coin
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface CoinGeckoService {
    @GET("coins/markets")
    fun getCoins(
        @Query("vs_currency")currency: String = "usd",
        @Query("per_page")perPage: Int = 20): Call<List<Coin>>
}