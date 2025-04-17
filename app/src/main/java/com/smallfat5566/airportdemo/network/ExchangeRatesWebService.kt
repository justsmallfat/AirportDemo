package com.smallfat5566.airportdemo.network

import android.content.Context
import com.smallfat5566.airportdemo.models.APISampleResponse
import com.smallfat5566.airportdemo.utils.NetworkUtils
import com.smallfat5566.airportdemo.utils.ProgressDialogUtil
import com.smallfat5566.airportdemo.utils.SimpleErrorHandleUtils
import com.smallfat5566.airportdemo.R
import com.smallfat5566.airportdemo.utils.AppConstant.API.Freecurrencyapi_Key
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class ExchangeRatesWebService (context: Context,
                               val coroutineScope: CoroutineScope,
                               showProgressDialog: Boolean = true
) : BaseWebService(context, showProgressDialog){



    fun initApiService(): ExchangeRatesAPIService {
        // 設定 Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()) // 自動解析 JSON
            .build()

        // 創建 API 服務
        return retrofit.create(ExchangeRatesAPIService::class.java)
    }

    suspend fun <T> fetchData(
        context: Context,
        default: T,
        apiCall: suspend ExchangeRatesAPIService.() -> T
    ): T = withContext(Dispatchers.IO) {
        if (showProgressDialog) {
            coroutineScope.launch(Dispatchers.Main) {
                ProgressDialogUtil.showProgressDialog(context)
            }
        }
        val result: T

        if (NetworkUtils.isNetworkAvailable(context)) {
            val exchangeReportAPIService = ExchangeRatesWebService(context, coroutineScope, true).initApiService()
            result = try {
                apiCall(exchangeReportAPIService)
            } catch (e: IOException) {
                SimpleErrorHandleUtils.errorSampleHandle(context, e.message ?: e.toString())
                default
            } catch (e: HttpException) {
                SimpleErrorHandleUtils.errorSampleHandle(context, e.message ?: e.toString())
                default
            } catch (e: Exception) {
                SimpleErrorHandleUtils.errorSampleHandle(context, e.message ?: e.toString())
                default
            }
        } else {
            SimpleErrorHandleUtils.errorSampleHandle(context, context.resources.getString(R.string.error_message_no_network))
            result = default
        }
        coroutineScope.launch(Dispatchers.Main) {
            ProgressDialogUtil.dismiss()
        }
        return@withContext result
    }

    suspend fun getRates(context: Context,
                         apikey: String = Freecurrencyapi_Key,
                         baseCurrency: String? = "USD",
                         currencies: String? = null ): APISampleResponse =
        fetchData(context, default = APISampleResponse(data = emptyMap())) {
            getRates(apikey = apikey, baseCurrency = baseCurrency, currencies = currencies)
        }

}