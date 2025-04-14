package com.smallfat5566.airportdemo.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smallfat5566.airportdemo.network.ExchangeRatesAPIService
import com.smallfat5566.airportdemo.network.ExchangeRatesWebService
import com.smallfat5566.airportdemo.utils.AppConstant.API.Freecurrencyapi_Key
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExchangeRateViewModel : ViewModel() {


    fun fetchRates(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val exchangeReportAPIService = ExchangeRatesWebService(context, viewModelScope, true)
            val res = exchangeReportAPIService.getRates(context)
            Log.d("fetchRates","fetchRates : ${res}")
        }
    }
}