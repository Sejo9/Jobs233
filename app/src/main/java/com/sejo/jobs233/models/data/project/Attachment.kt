package com.sejo.jobs233.models.data.project

data class Attachment(
    val created_at: String,
    val format: String,
    val id: Int,
    val location: String,
    val name: String,
    val project_id: Int,
    val size: String,
    val updated_at: String,
    val user_id: Int
)