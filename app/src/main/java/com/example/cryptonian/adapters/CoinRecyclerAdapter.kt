package com.example.cryptonian.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptonian.R
import com.example.cryptonian.api.dto.Coin
import com.example.cryptonian.fragments.HomeFragmentDirections
import com.squareup.picasso.Picasso
import androidx.navigation.fragment.findNavController

class CoinRecyclerAdapter(private val coins: List<Coin>): RecyclerView.Adapter<CoinRecyclerAdapter.CoinViewHolder>() {
    class CoinViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val icon: ImageView = itemView.findViewById(R.id.iconView)
        private val name: TextView = itemView.findViewById(R.id.name)
        private val symbol: TextView = itemView.findViewById(R.id.symbol)
        private val price: TextView = itemView.findViewById(R.id.currentPrice)
        private val high: TextView = itemView.findViewById(R.id.high)
        private val low: TextView = itemView.findViewById(R.id.low)
        private val rank: TextView = itemView.findViewById(R.id.rank)
        private lateinit var coin: Coin
        init {
            itemView.setOnClickListener(this)
        }

        fun onBind(coin: Coin) {
            Picasso.get().load(coin.image).into(icon)
            name.text = coin.id.replaceFirstChar(Char::titlecase)
            symbol.text = coin.symbol
            price.text = "Current price: ${coin.price} USD"
            high.text = "⬆ ${coin.high.toString()}"
            low.text = "⬇ ${coin.low.toString()}"
            rank.text = "Rank: ${coin.rank.toString()}"
            this.coin = coin
        }

        override fun onClick(clickedView: View?) {
            val rank = coin.rank?: 1
            val action = HomeFragmentDirections.actionHomeFragmentToConverterFragment(rank)
            clickedView?.findNavController()?.navigate(action)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_coin, parent, false)
        return CoinViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.onBind(coins[position])
    }

    override fun getItemCount() = coins.size
}