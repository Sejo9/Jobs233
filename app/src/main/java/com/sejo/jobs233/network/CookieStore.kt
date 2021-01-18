package com.sejo.jobs233.network

import android.content.Context
import android.util.Log
import androidx.preference.PreferenceManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException

private const val cookiesKey = "appCookies"

class SendSavedCookiesInterceptor(private val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val preferences = PreferenceManager
            .getDefaultSharedPreferences(context)
            .getStringSet(cookiesKey, HashSet()) as HashSet<String>

        preferences.forEach {
            builder.addHeader("Cookie", it)
            Log.d("SendCookie", it)
        }

        return chain.proceed(builder.build())
    }
}

class SaveReceivedCookiesInterceptor(private val context: Context) : Interceptor {

    @JvmField
    val setCookieHeader = "Set-Cookie"

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())

        if (originalResponse.headers(setCookieHeader).isNotEmpty() &&
            (PreferenceManager.getDefaultSharedPreferences(context)
                .getStringSet(cookiesKey, HashSet()) as HashSet<String>).isEmpty()
        ) {
            val cookies = PreferenceManager
                .getDefaultSharedPreferences(context)
                .getStringSet(cookiesKey, HashSet()) as HashSet<String>

            originalResponse.headers(setCookieHeader).forEach {
                cookies.add(it)
                Log.d("SaveCookie", it)
            }

            PreferenceManager
                .getDefaultSharedPreferences(context)
                .edit()
                .putStringSet(cookiesKey, cookies)
                .apply()
        }

        return originalResponse
    }

}

fun OkHttpClient.Builder.setCookieStore(context: Context): OkHttpClient.Builder {
    return this
        .addInterceptor(SendSavedCookiesInterceptor(context))
        .addInterceptor(SaveReceivedCookiesInterceptor(context))
}