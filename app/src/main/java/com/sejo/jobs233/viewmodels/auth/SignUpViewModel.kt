package com.sejo.jobs233.viewmodels.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sejo.jobs233.R
import com.sejo.jobs233.activities.AuthActivity
import com.sejo.jobs233.models.responses.RegisterResponse
import com.sejo.jobs233.network.API
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SignUpViewModel : ViewModel() {

    private val _registerResponse = MutableLiveData<RegisterResponse>()
    val registerResponse: LiveData<RegisterResponse>
        get() = _registerResponse

    suspend fun register() {
        withContext(Dispatchers.IO) {
            try {
                _registerResponse.value = API.instance.registerUser(
                    AuthActivity.firstName, AuthActivity.lastName,
                    AuthActivity.username, AuthActivity.email, AuthActivity.password,
                    AuthActivity.preference
                )
            } catch (e: Exception) {
                Log.e("SignUpViewModel", e.toString())
            }
        }
    }

    fun setPreference(acc_type_id: Int) {
        when (acc_type_id) {
            R.id.work_radio_btn -> AuthActivity.preference = "work"
            R.id.hire_radio_btn -> AuthActivity.preference = "hire"
        }
    }
}