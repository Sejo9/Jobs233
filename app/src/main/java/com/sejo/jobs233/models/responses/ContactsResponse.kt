package com.sejo.jobs233.models.responses

import com.sejo.jobs233.models.data.inbox.MessageContact

data class ContactsResponse(
    val contacts: List<MessageContact>,
    val success: Boolean
)