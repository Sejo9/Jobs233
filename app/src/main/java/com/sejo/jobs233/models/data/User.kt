package com.sejo.jobs233.models.data

data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val email_verified_at: String?,
    val created_at: String,
    val updated_at: String,
    val projects_count: Int,
    val profile: Profile
)