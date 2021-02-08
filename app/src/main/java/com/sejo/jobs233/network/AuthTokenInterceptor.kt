package com.sejo.jobs233.network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class AuthTokenInterceptor(private val context: Context) : Interceptor {

    private val tokenStorage = TokenStorage(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        // If token has been saved, add it to the request
        if (!tokenStorage.fetchAuthToken().isNullOrEmpty()) {
            requestBuilder.addHeader("Authorization", "Bearer ${tokenStorage.fetchAuthToken()}")
        }

        return chain.proceed(requestBuilder.build())
    }

}