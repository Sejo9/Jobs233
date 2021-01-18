package com.sejo.jobs233.viewmodels.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ForgotPasswordViewModel : ViewModel() {

    private val _emailIsBlank = MutableLiveData<Boolean>()
    val emailIsBlank: LiveData<Boolean>
        get() = _emailIsBlank

    private val _linkSent = MutableLiveData<Boolean>()
    val linkSent: LiveData<Boolean>
        get() = _linkSent

    suspend fun resetPassword(email: String) {

        withContext(Dispatchers.IO) {
            if (email.isBlank()) {
                _emailIsBlank.value = true
            }

            /*val response = API.instance.forgotPassword(email)

            if(response.success){
                _linkSent.value = true
            }*/

            _linkSent.value = true
        }

    }
}