package com.sejo.jobs233.viewmodels.auth

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.preference.PreferenceManager
import com.sejo.jobs233.database.JobsDatabase
import com.sejo.jobs233.extra.asProfileEntity
import com.sejo.jobs233.extra.asUserEntity
import com.sejo.jobs233.network.API
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginViewModel(private val context: Context) : ViewModel() {

    private val _emailIsEmpty = MutableLiveData<Boolean>()
    val emailIsEmpty: LiveData<Boolean>
        get() = _emailIsEmpty

    private val _passwordIsEmpty = MutableLiveData<Boolean>()
    val passwordIsEmpty: LiveData<Boolean>
        get() = _passwordIsEmpty

    private val _loginError = MutableLiveData<Boolean>()
    val loginError: LiveData<Boolean>
        get() = _loginError

    private val _loginErrorCode = MutableLiveData<Int>()
    val loginErrorCode: LiveData<Int>
        get() = _loginErrorCode

    private val _loginComplete = MutableLiveData<Boolean>()
    val loginComplete: LiveData<Boolean>
        get() = _loginComplete

    suspend fun login(identity: String, password: String) {
        withContext(Dispatchers.IO) {

            val response = API.instance.loginUser(identity, password)

            when (response.code()) {
                204 -> getUserDetails()
                422 -> _loginErrorCode.value = 422
                else -> {
                    _loginErrorCode.value = response.code()
                    Log.e("LoginViewModel", response.errorBody().toString())
                }
            }
        }

    }

    private suspend fun getUserDetails() {
        withContext(Dispatchers.IO) {
            try {
                val response = API.instance.getUser()

                val db = JobsDatabase.getInstance(context)
                db.userDao.insertUser(response.asUserEntity())
                db.profileDao.insertProfile(response.asProfileEntity())

                PreferenceManager.getDefaultSharedPreferences(context).edit().apply {
                    putString("fullname", response.name)
                    putString("username", response.username)
                    putString("profile_pic_url", response.profile.picture)
                    putString("email", response.email)
                    putString("phone", response.profile.phone_number)
                    putString("gender", response.profile.gender)
                    putString("bio", response.profile.bio)
                    putString("country", response.profile.country)
                    putString("city", response.profile.city)
                    putString("address", response.profile.address)
                    putBoolean("isLoggedIn", true)
                    apply()
                }

                _loginComplete.value = true
            } catch (e: Exception) {
                Log.e("LoginViewModel", e.toString())
            }
        }
    }

    fun validateCredentials(email: String, password: String) {
        when {
            email.isEmpty() -> _emailIsEmpty.value = true
            password.isEmpty() -> _passwordIsEmpty.value = true
            else -> _loginError.value = false
        }
    }
}