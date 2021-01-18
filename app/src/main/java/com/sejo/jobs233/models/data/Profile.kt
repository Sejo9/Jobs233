package com.sejo.jobs233.models.data

data class Profile(
    val id: Int,
    val user_id: Int,
    val picture: String,
    val gender: String?,
    val phone_number: String?,
    val country: String?,
    val city: String?,
    val address: String?,
    val bio: String?,
    val preference: String,
    val is_occupied: Int,
    val preferred_currency_id: Int,
    val created_at: String,
    val updated_at: String
)