package com.sejo.jobs233.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.sejo.jobs233.R
import com.sejo.jobs233.network.API

class AuthActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }


    override fun onStart() {
        super.onStart()

        API.APIContext = this.applicationContext

        if (checkLoginStatus()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }


    private fun checkLoginStatus(): Boolean =
        PreferenceManager.getDefaultSharedPreferences(this).getBoolean("isLoggedIn", false)


    companion object {
        const val LOGIN_PREFERENCE = "loginPref"

        val retroTag = "RETROFIT_ERROR"

        lateinit var firstName: String
        lateinit var lastName: String
        lateinit var username: String
        lateinit var email: String
        lateinit var password: String
        lateinit var preference: String
    }
}