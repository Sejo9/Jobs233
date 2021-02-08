package com.sejo.jobs233.viewmodels.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sejo.jobs233.database.JobsDatabase
import com.sejo.jobs233.repositories.UserRepository

class DashboardViewModel(val context: Context) : ViewModel() {

    private val _monthEarnings = MutableLiveData<String>()
    val monthEarnings: LiveData<String>
        get() = _monthEarnings

    private val database = JobsDatabase.getInstance(context)
    private val userRepo = UserRepository(database, context)

    val userInfo = userRepo.userInfo
    val walletInfo = userRepo.walletInfo
    val profileInfo = userRepo.profileInfo
    val currencyInfo = userRepo.currencyInfo

    fun calculateProfilePercentage(): Int {
        var completeFields = 0
        val profile = profileInfo.value

        if (!profile?.phone_number.isNullOrEmpty()) {
            completeFields = completeFields + 1
        }

        if (!profile?.title.isNullOrEmpty()) {
            completeFields = completeFields + 1
        }

        if (!profile?.gender.isNullOrEmpty()) {
            completeFields = completeFields + 1
        }

        if (!profile?.bio.isNullOrEmpty()) {
            completeFields = completeFields + 1
        }

        if (!profile?.country.isNullOrEmpty()) {
            completeFields = completeFields + 1
        }

        if (!profile?.city.isNullOrEmpty()) {
            completeFields = completeFields + 1
        }

        if (!profile?.address.isNullOrEmpty()) {
            completeFields = completeFields + 1
        }

        return completeFields
    }

}