package com.sejo.jobs233.models.responses

import com.sejo.jobs233.models.data.inbox.Notification

data class NotificationsResponse(
    val success: Boolean,
    val notifications: List<Notification>
)
