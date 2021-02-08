package com.sejo.jobs233.viewmodels.factories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sejo.jobs233.viewmodels.profile.EditProfileViewModel

class EditProfileViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditProfileViewModel::class.java)) {
            return EditProfileViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}