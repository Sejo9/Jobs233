package com.sejo.jobs233.viewmodels.profile

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.sejo.jobs233.database.JobsDatabase
import com.sejo.jobs233.network.API
import com.sejo.jobs233.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfileViewModel(val context: Context) : ViewModel() {

    private val database = JobsDatabase.getInstance(context)
    private val userRepo = UserRepository(database, context)

    val userInfo = userRepo.userInfo
    val profileInfo = userRepo.profileInfo

    suspend fun refreshUserInfo() {
        withContext(Dispatchers.IO) {
            userRepo.setUserDetails()
        }
    }

    suspend fun logout() {
        withContext(Dispatchers.IO) {
            val response = API.instance.logoutUser()

            if (response.code() != 200) {
                Toast.makeText(context, "Logout Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}