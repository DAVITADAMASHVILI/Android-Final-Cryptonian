package com.example.cryptonian.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.cryptonian.adapters.CoinRecyclerAdapter
import com.example.cryptonian.api.RestClient
import com.example.cryptonian.databinding.ActivityMainBinding
import com.example.cryptonian.room.App
import com.example.cryptonian.workers.FetchCoinsWork
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        RestClient.initClient()

        binding
            .recyclerView
            .addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        val constraint = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val coinsWorker = OneTimeWorkRequestBuilder<FetchCoinsWork>()
            .setConstraints(constraint)
            .build()

        WorkManager.getInstance(this)
            .beginWith(coinsWorker)
            .enqueue()
            .result
            .addListener({ onCoinsFetched() }, { command -> command?.run() })
    }

    private fun onCoinsFetched() {
        try {
            val coins = App.instance.database.getCoinDao().getAllCoins()
            coins.forEach {
                Log.i("DISPLAY_COIN", it.toString())
            }
            binding.recyclerView.adapter = CoinRecyclerAdapter(coins)
            binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        } catch (e: RuntimeException) {
            Log.i("DISPLAY_COIN", e.message.toString())
        }
    }
}