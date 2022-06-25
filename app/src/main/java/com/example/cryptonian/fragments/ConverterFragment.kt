package com.example.cryptonian.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cryptonian.R
import com.example.cryptonian.api.dto.Coin
import com.example.cryptonian.databinding.FragmentConverterBinding
import com.example.cryptonian.room.App

class ConverterFragment: Fragment(R.layout.fragment_converter) {
    private var _binding: FragmentConverterBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedCoin: Coin

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConverterBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            calculate()
        }
        var selectedCoinRank = ConverterFragmentArgs.fromBundle(requireArguments()).rank
        setupSpinner(selectedCoinRank)
    }

    private fun setupSpinner(rank: Int = 1) {
        try {
            val coins = App.instance.database.getCoinDao().getAllCoins()
            val adapter = ArrayAdapter(context!!,
                android.R.layout.simple_spinner_dropdown_item,
                coins.map { it.symbol })

            binding.spinner.adapter = adapter
            binding.spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, index: Int, p3: Long) {
                    selectedCoin = coins[index]
                    binding.textInputLayout.hint = "Enter ${selectedCoin.id}(${selectedCoin.symbol})"
                }
                override fun onNothingSelected(p0: AdapterView<*>?) { return }
            }
            binding.spinner.setSelection(rank - 1)
        } catch (error: RuntimeException) {
            Toast.makeText(
                context,
                error.message.toString(),
                Toast.LENGTH_SHORT
            ).show()
            Log.i("FETCH_COINS", error.message.toString())
        }
    }

    private fun calculate() {
        val enteredAmount = binding.amountField.text.toString().toDoubleOrNull()?: return
        val coinPrice = selectedCoin.price?: return
        val result = enteredAmount * coinPrice
        binding.resultText.text = "$enteredAmount ${selectedCoin.symbol} = $result USD"
    }


}