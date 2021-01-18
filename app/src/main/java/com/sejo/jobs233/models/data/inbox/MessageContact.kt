package com.sejo.jobs233.models.data.inbox

import com.sejo.jobs233.models.data.User

data class MessageContact(
    val id: Int,
    val user_id: Int,
    val related_user_id: Int,
    val chat_enabled: Int,
    val is_blocked: Int,
    val created_at: String?,
    val updated_at: String?,
    val user: User?
)