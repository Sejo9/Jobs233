package com.sejo.jobs233.models.data.project

import com.sejo.jobs233.models.data.User

data class ProjectAdapterItem(
    val id: Int,
    val title: String,
    val budget: String,
    val description: String,
    val tags: String,
    val skills: String,
    val client: User
)