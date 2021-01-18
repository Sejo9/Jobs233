package com.sejo.jobs233.fragments.settings

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.sejo.jobs233.R
import com.sejo.jobs233.activities.AuthActivity
import com.sejo.jobs233.network.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceClickListener {

    private val retroTag = "RETROFIT_ERROR"

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        val preferences = listOf("profile_pref", "notifications_pref", "about_pref", "logout_pref")

        preferences.forEach {
            findPreference<Preference>(it)?.onPreferenceClickListener = this
        }
    }

    private fun logout() {

        API.instance.logoutUser().enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    setLoginPreference()

                    startActivity(Intent(activity?.applicationContext, AuthActivity::class.java))
                    activity?.finish()
                } else {
                    Toast.makeText(
                        activity?.applicationContext,
                        "Logout failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e(retroTag, t.message!!)
            }
        })
    }


    //Sets the isLoggedIn preference to false
    private fun setLoginPreference() {
        PreferenceManager.getDefaultSharedPreferences(activity?.applicationContext).edit().apply {
            this.remove("appCookies")
            this.putBoolean("isLoggedIn", false)
            apply()
        }
    }

    override fun onPreferenceClick(preference: Preference?): Boolean {
        when (preference?.key) {
            "logout_pref" -> logout()
            "profile_pref" -> findNavController().navigate(R.id.action_settingsFragment_to_editProfileFragment)
            "notifications_pref" -> Toast.makeText(
                activity?.applicationContext,
                "Notifications",
                Toast.LENGTH_SHORT
            ).show()
            "about_pref" -> Toast.makeText(
                activity?.applicationContext,
                "About Jobs233",
                Toast.LENGTH_SHORT
            ).show()
        }
        return true
    }


}