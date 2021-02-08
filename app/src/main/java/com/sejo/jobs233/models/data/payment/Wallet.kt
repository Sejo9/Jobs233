package com.sejo.jobs233.models.data.payment

data class Wallet(
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
