package com.sejo.jobs233.models.data.project

import com.sejo.jobs233.models.data.Link

data class ProjectsContent(
    val current_page: Int,
    val data: List<Project>,
    val first_page_url: String?,
    val from: Int,
    val last_page: Int,
    val last_page_url: String,
    val links: List<Link>,
    val next_page_url: String?,
    val path: String,
    val per_page: Int,
    val prev_page: String?,
    val to: Int,
    val total: Int
)