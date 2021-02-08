package com.sejo.jobs233.viewmodels.profile

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.sejo.jobs233.database.JobsDatabase
import com.sejo.jobs233.network.API
import com.sejo.jobs233.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EditProfileViewModel(val context: Context) : ViewModel() {

    private val database = JobsDatabase.getInstance(context)
    private val userRepo = UserRepository(database, context)

    val userInfo = userRepo.userInfo
    val profileInfo = userRepo.profileInfo

    suspend fun updateProfile(
        firstName: String,
        lastName: String,
        title: String,
        phoneNumber: String,
        gender: String,
        bio: String,
        country: String,
        city: String,
        address: String
    ): Boolean {
        return withContext(Dispatchers.IO) {
            val response = API.instance.updateProfile(
                firstName,
                lastName,
                title,
                phoneNumber,
                gender,
                bio,
                country,
                city,
                address
            )
            Log.d("UpdateProfile", response.toString())
            if (response.code() == 301 || response.code() == 200) {
                userRepo.setUserDetails()
                true
            } else false
        }
    }

}