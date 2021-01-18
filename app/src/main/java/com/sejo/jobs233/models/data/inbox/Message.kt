package com.sejo.jobs233.models.data.inbox

import com.sejo.jobs233.models.data.User

data class Message(
    val id: Int,
    val user_id: Int,
    val message: String,
    val receiver_id: Int,
    val created_at: String,
    val updated_at: String,
    val user: User
)