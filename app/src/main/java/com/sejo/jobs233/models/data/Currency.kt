package com.sejo.jobs233.models.data

data class Currency(
    val id: Int,
    val name: String,
    val exchange_rate_in_usd: Double,
    val symbol: String,
    val created_at: String?,
    val updated_at: String?
)