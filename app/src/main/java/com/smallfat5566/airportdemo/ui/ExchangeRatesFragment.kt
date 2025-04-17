package com.smallfat5566.airportdemo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.smallfat5566.airportdemo.databinding.FragmentExchangeRateBinding
import com.smallfat5566.airportdemo.ui.adapter.ExchangeRatesAdapter
import com.smallfat5566.airportdemo.ui.dialog.ExchangeCurrencyBottomSheetDialog

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
            viewModel.currentBaseCurrency.postValue(item.key)
            viewModel.fetchRates(fragContext) { rates ->
                val dialog = ExchangeCurrencyBottomSheetDialog()
                dialog.show(childFragmentManager, "")
            }
        }


        binding.exchangeRatesRecyclerView.layoutManager = LinearLayoutManager(fragContext)
        binding.exchangeRatesRecyclerView.adapter = adapter

        viewModel.allExchangeRates.observe(viewLifecycleOwner) { exchangeRates ->
            adapter.submitList(exchangeRates)
        }

        viewModel.currentBaseCurrency.observe(viewLifecycleOwner) { baseCurrency ->
            binding.currentBaseCurrencyTextView.setText(baseCurrency)
        }

        viewModel.fetchRates(fragContext){}

        return root
    }

}