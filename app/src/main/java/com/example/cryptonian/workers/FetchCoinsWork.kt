package com.example.cryptonian.workers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.cryptonian.api.RestClient
import com.example.cryptonian.api.dto.Coin
import com.example.cryptonian.room.App
import java.lang.RuntimeException

class FetchCoinsWork (ctx: Context, params: WorkerParameters): Worker(ctx, params) {
    override fun doWork(): Result {
        try {
            var fetchedcoins = listOf<Coin>()
            fetchedcoins += RestClient.reqResApi.getCoins().execute().body()?: listOf()

            fetchedcoins.forEach {
                Log.i("GET_COIN", it.toString())
                App.instance.database.getCoinDao().insert(it)
            }

        } catch (e: RuntimeException) {
            Log.i("GET_COIN", e.message.toString())
            return Result.failure()
        }
        return Result.success()
    }
}