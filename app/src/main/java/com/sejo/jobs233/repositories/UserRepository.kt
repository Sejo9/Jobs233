package com.sejo.jobs233.repositories

import android.content.Context
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.preference.PreferenceManager
import com.sejo.jobs233.database.JobsDatabase
import com.sejo.jobs233.database.entity.CurrencyEntity
import com.sejo.jobs233.database.entity.ProfileEntity
import com.sejo.jobs233.database.entity.UserEntity
import com.sejo.jobs233.database.entity.WalletEntity
import com.sejo.jobs233.extra.asCurrencyEntity
import com.sejo.jobs233.extra.asProfileEntity
import com.sejo.jobs233.extra.asUserEntity
import com.sejo.jobs233.extra.asWalletEntity
import com.sejo.jobs233.network.API
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val database: JobsDatabase, private val context: Context) {

    private var userID: Int = 0
    private var profileID: Int = 0
    private var walletID: Int = 0
    private var currencyID: Int = 0

    init {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        userID = prefs.getInt("userID", 0)
        profileID = prefs.getInt("profileID", 0)
        walletID = prefs.getInt("walletID", 0)
        currencyID = prefs.getInt("currencyID", 0)
    }

    val userInfo: LiveData<UserEntity> = database.userDao.retrieveUser(userID)
    val profileInfo: LiveData<ProfileEntity> = database.profileDao.retrieveProfile(profileID)
    val walletInfo: LiveData<WalletEntity> = database.walletDao.retrieveWallet(walletID)
    val currencyInfo: LiveData<CurrencyEntity> = database.currencyDao.retrieveCurrency(currencyID)

    suspend fun setUserDetails() {
        withContext(Dispatchers.IO) {
            val user = API.instance.getUser()
            database.userDao.insertUser(user.asUserEntity())
            database.profileDao.insertProfile(user.asProfileEntity())
            database.walletDao.insertWallet(user.asWalletEntity())
            database.currencyDao.insertCurrency(user.asCurrencyEntity())
            PreferenceManager.getDefaultSharedPreferences(context).edit {
                putInt("userID", user.id)
                putInt("profileID", user.profile.id)
                putInt("walletID", user.wallet.id)
                putInt("currencyID", user.profile.currency.id)
            }
        }
    }
}