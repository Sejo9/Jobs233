package com.sejo.jobs233.models.responses

import com.sejo.jobs233.models.data.inbox.MessagesContent

data class MessagesResponse(
    val messages: MessagesContent,
    val success: Boolean
)