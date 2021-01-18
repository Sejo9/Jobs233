package com.sejo.jobs233.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val email_verified_at: String?,
    val created_at: String,
    val updated_at: String,
    val projects_count: Int,
    val profileID: Int
)