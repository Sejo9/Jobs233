package com.sejo.jobs233.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

const val BASE_SITE_URL = "http://192.168.43.161:8000"

fun checkNetworkConnection(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    return true
                }
            }
        }

        return false
    } else {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo

        if (activeNetworkInfo != null) {
            return true
        }

        return false
    }

}