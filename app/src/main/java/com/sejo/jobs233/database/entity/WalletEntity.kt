package com.sejo.jobs233.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wallet_table")
data class WalletEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val holder_type: String,
    val holder_id: Int,
    val name: String,
    val slug: String,
    val description: String?,
    //val meta: List<String>,
    val balance: String,
    val decimal_places: Int,
    val created_at: String,
    val updated_at: String
)
