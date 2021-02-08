package com.sejo.jobs233.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sejo.jobs233.database.entity.WalletEntity

@Dao
interface WalletDao {

    @Insert
    fun insertWallet(wallet: WalletEntity)

    @Update
    fun updateWallet(wallet: WalletEntity)

    @Query("SELECT * FROM wallet_table WHERE id = :walletID")
    fun retrieveWallet(walletID: Int): LiveData<WalletEntity>

    @Delete
    fun deleteWallet(wallet: WalletEntity)
}