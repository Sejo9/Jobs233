package com.sejo.jobs233.network

import android.content.Context
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class TokenStorage(context: Context) {
    private var prefs = PreferenceManager.getDefaultSharedPreferences(context)

    companion object {
        const val USER_TOKEN = "user_token"
    }

    /**
     * Function to save auth token
     */
    fun saveAuthToken(token: String) {
        prefs.edit {
            putString(USER_TOKEN, token)
        }
    }

    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }
}