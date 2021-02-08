package com.sejo.jobs233.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val first_name: String,
    val last_name: String,
    val username: String,
    val email: String,
    val email_verified_at: String?,
    val created_at: String,
    val updated_at: String,
    val is_online: Int,
    val projects_count: Int,
    val assigned_projects_to_worker_count: Int,
    val my_assigned_projects_count: Int,
    val completed_projects_count: Int,
    val completed_assigned_projects_count: Int,
    val profileID: Int,
    val walletID: Int
)