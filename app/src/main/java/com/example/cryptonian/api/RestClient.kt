package com.example.cryptonian.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestClient {
    private lateinit var retrofit: Retrofit
    private lateinit var okHttpClient: OkHttpClient

    val BASE_URL = "https://api.coingecko.com/api/v3/"

    fun initClient() {
        okHttpClient = OkHttpClient.Builder().build()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun<S> getService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }

    val reqResApi: CoinGeckoService
        get() = getService(CoinGeckoService::class.java)
}