package com.sejo.jobs233.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_table")
data class CurrencyEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val exchange_rate_in_usd: Double,
    val symbol: String,
    val created_at: String?,
    val updated_at: String?
)