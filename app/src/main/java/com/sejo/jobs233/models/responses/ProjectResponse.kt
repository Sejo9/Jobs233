package com.sejo.jobs233.models.responses

import com.sejo.jobs233.models.data.project.Project

data class ProjectResponse(
    val success: Boolean,
    val project: Project
)