package com.sejo.jobs233.fragments.settings

import android.os.Bundle
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.sejo.jobs233.R
import com.sejo.jobs233.network.checkNetworkConnection

class EditProfileFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.edit_profile_preferences, rootKey)

        val preferences = listOf("fullname", "username", "email", "phone")

        preferences.forEach {
            findPreference<EditTextPreference>(it)?.onPreferenceChangeListener =
                Preference.OnPreferenceChangeListener { preference, newValue ->
                    if (checkNetworkConnection(activity?.applicationContext!!)) {
                        updatePreference()
                        true
                    } else {

                        false
                    }
                }
        }
    }

    private fun updatePreference(): Boolean {
        return true
    }
}