package com.smallfat5566.airportdemo.network

import com.smallfat5566.airportdemo.models.APISampleResponse
import retrofit2.http.GET
import retrofit2.http.Query

private const val prefix = "/v1/"
interface ExchangeRatesAPIService {
    @GET(prefix + "latest")
    suspend fun getRates(@Query("apikey") apikey: String,
                         @Query("base_currency") base_currency: String,
                         @Query("currencies") currencies: String? ): APISampleResponse

}