package com.smallfat5566.airportdemo.ui

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smallfat5566.airportdemo.R
import kotlinx.coroutines.Dispatchers

class ExchangeRateFragment : AbstractFragment() {

    companion object {
        fun newInstance() = ExchangeRateFragment()
    }

    private val viewModel: ExchangeRateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        viewModel.fetchRates(fragContext)

        return inflater.inflate(R.layout.fragment_exchange_rate, container, false)
    }

}