package com.sejo.jobs233.models.responses

import com.sejo.jobs233.models.data.project.ProjectsContent

data class AllProjectsResponse(
    val projects: ProjectsContent,
    val success: Boolean
)