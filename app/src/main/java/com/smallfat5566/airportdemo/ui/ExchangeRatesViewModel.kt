package com.smallfat5566.airportdemo.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smallfat5566.airportdemo.network.ExchangeRatesWebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExchangeRatesViewModel : ViewModel() {

    val allExchangeRates = MutableLiveData<List<Map.Entry<String, Double>>>().apply {
        value = ArrayList<Map.Entry<String, Double>>()
    }

    var currentBaseCurrency = MutableLiveData<String>().apply {
        value = "USD"
    }

    fun fetchRates(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val exchangeReportAPIService = ExchangeRatesWebService(context, viewModelScope, true)
            val res = exchangeReportAPIService.getRates(context, baseCurrency = currentBaseCurrency.value)
            allExchangeRates.postValue(res.data.entries.toList())
        }
    }
}