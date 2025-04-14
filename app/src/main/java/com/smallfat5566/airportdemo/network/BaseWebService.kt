package com.smallfat5566.airportdemo.network

import android.content.Context
import com.smallfat5566.airportdemo.MyApplication
import com.smallfat5566.airportdemo.network.WebServiceConfig.CONNECT_TIMEOUT
import com.smallfat5566.airportdemo.network.WebServiceConfig.READ_TIMEOUT
import com.smallfat5566.airportdemo.network.WebServiceConfig.WRITE_TIMEOUT
import com.smallfat5566.airportdemo.utils.AppConstant.API.Freecurrencyapi_Domain
import com.smallfat5566.airportdemo.utils.AppConstant.API.Freecurrencyapi_Protocol
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object WebServiceConfig {
    const val CONNECT_TIMEOUT = 30L
    const val READ_TIMEOUT = 30L
    const val WRITE_TIMEOUT = 30L
}

abstract class BaseWebService(
    context: Context,
    var showProgressDialog: Boolean = true,
    var webServiceContext: Context = context,
) {
    private val TAG = this.javaClass.simpleName
    val baseURL = Freecurrencyapi_Protocol + Freecurrencyapi_Domain
    private val myApplication = webServiceContext.applicationContext as MyApplication

    // 設定 OkHttpClient
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val okHttpClient = OkHttpClient.Builder()
//        .addInterceptor(loggingInterceptor) // 記錄請求與回應
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS) // 設定連線超時
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        .build()
}