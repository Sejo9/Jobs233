package com.sejo.jobs233.viewmodels.auth

import android.content.Context
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.preference.PreferenceManager
import com.sejo.jobs233.database.JobsDatabase
import com.sejo.jobs233.network.API
import com.sejo.jobs233.network.TokenStorage
import com.sejo.jobs233.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginViewModel(private val context: Context) : ViewModel() {

    private val _emailIsEmpty = MutableLiveData<Boolean>()
    val emailIsEmpty: LiveData<Boolean>
        get() = _emailIsEmpty

    private val _passwordIsEmpty = MutableLiveData<Boolean>()
    val passwordIsEmpty: LiveData<Boolean>
        get() = _passwordIsEmpty

    private val _credentialsError = MutableLiveData<Boolean>()
    val credentialsError: LiveData<Boolean>
        get() = _credentialsError

    private val _loginErrorCode = MutableLiveData<Int>()
    val loginErrorCode: LiveData<Int>
        get() = _loginErrorCode

    private val _loginComplete = MutableLiveData<Boolean>()
    val loginComplete: LiveData<Boolean>
        get() = _loginComplete


    private val database = JobsDatabase.getInstance(context)
    private val userRepo = UserRepository(database, context)

    private suspend fun setUserDetails() {
        withContext(Dispatchers.IO) {
            try {

                userRepo.setUserDetails()

                PreferenceManager.getDefaultSharedPreferences(context)
                    .edit { putBoolean("isLoggedIn", true) }

                withContext(Dispatchers.Main) {
                    _loginComplete.value = true
                }
            } catch (e: Exception) {
                Log.e("LoginViewModel", e.toString())
            }
        }
    }

    fun validateCredentials(email: String, password: String) {
        when {
            email.isEmpty() -> _emailIsEmpty.value = true
            password.isEmpty() -> _passwordIsEmpty.value = true
            else -> _credentialsError.value = false
        }
    }

    suspend fun requestToken(email: String, password: String) {
        withContext(Dispatchers.IO) {
            val tokenStorage = TokenStorage(context)
            val deviceName = android.os.Build.MODEL
            val response = API.instance.requestToken(email, password, deviceName)

            when (response.code()) {
                200 -> {
                    tokenStorage.saveAuthToken(response.body()!!)
                    setUserDetails()
                }
                else -> {
                    withContext(Dispatchers.Main) {
                        _loginErrorCode.value = response.code()
                    }
                    Log.e("LoginError", response.toString())
                }
            }

        }
    }
}