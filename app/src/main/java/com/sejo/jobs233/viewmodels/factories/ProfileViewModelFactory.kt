package com.sejo.jobs233.viewmodels.factories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sejo.jobs233.viewmodels.profile.ProfileViewModel

class ProfileViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}