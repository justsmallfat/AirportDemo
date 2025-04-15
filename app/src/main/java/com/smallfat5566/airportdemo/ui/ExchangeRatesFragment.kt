package com.smallfat5566.airportdemo.ui

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.smallfat5566.airportdemo.databinding.FragmentExchangeRateBinding
import com.smallfat5566.airportdemo.ui.adapter.ExchangeRatesAdapter

class ExchangeRatesFragment : AbstractFragment() {

    companion object {
        fun newInstance() = ExchangeRatesFragment()
    }

    private val viewModel: ExchangeRatesViewModel by viewModels()

    private var _binding: FragmentExchangeRateBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {


        _binding = FragmentExchangeRateBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val adapter = ExchangeRatesAdapter { item ->
            Toast.makeText(fragContext, "點擊了: ${item.key}", Toast.LENGTH_LONG).show()
        }

        binding.exchangeRatesRecyclerView.layoutManager = LinearLayoutManager(fragContext)
        binding.exchangeRatesRecyclerView.adapter = adapter

        viewModel.allExchangeRates.observe(viewLifecycleOwner) { exchangeRates ->
            adapter.submitList(exchangeRates)
        }

        viewModel.fetchRates(fragContext)

        return root
    }

}