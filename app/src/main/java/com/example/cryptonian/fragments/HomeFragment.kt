package com.example.cryptonian.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptonian.R
import com.example.cryptonian.adapters.CoinRecyclerAdapter
import com.example.cryptonian.api.RestClient
import com.example.cryptonian.api.dto.Coin
import com.example.cryptonian.databinding.FragmentHomeBinding
import com.example.cryptonian.room.App
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment: Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RestClient.initClient()
        binding
            .recyclerView
            .addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        fetchCoins()
    }

    private fun fetchCoins() {
        binding.progressBar.visibility = View.VISIBLE
        App.instance.database.getCoinDao().deleteAll()
        RestClient.coinGeckoApi.getCoins().enqueue(object : Callback<List<Coin>?> {
            override fun onResponse(call: Call<List<Coin>?>, response: Response<List<Coin>?>) {
                if (response.isSuccessful) {
                    val coins = response.body()?: listOf()
                    try {
                        coins.forEach {
                            App.instance.database.getCoinDao().insert(it)
                        }
                    } catch (error: RuntimeException) {
                        Toast.makeText(
                            context,
                            error.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.i("FETCH_COINS", error.message.toString())
                    }
                    Log.i("FETCH_COINS", coins.size.toString())
                    binding.recyclerView.adapter = CoinRecyclerAdapter(coins)
                    binding.recyclerView.layoutManager = LinearLayoutManager(context)
                    binding.progressBar.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<List<Coin>?>, t: Throwable) {
                Toast.makeText(context, t.message.toString(), Toast.LENGTH_SHORT).show()
                Log.i("FETCH_COINS", t.message.toString())
            }
        })
    }
}