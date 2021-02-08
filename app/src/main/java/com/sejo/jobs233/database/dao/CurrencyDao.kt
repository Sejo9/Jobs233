package com.sejo.jobs233.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sejo.jobs233.database.entity.CurrencyEntity

@Dao
interface CurrencyDao {
    @Insert
    fun insertCurrency(currency: CurrencyEntity)

    @Update
    fun updateCurrency(currency: CurrencyEntity)

    @Query("SELECT * FROM currency_table WHERE id = :currencyID")
    fun retrieveCurrency(currencyID: Int): LiveData<CurrencyEntity>

    @Delete
    fun deleteCurrency(currency: CurrencyEntity)
}