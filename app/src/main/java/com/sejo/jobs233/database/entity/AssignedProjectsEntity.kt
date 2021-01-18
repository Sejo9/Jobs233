package com.sejo.jobs233.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "assigned_projects_table")
data class AssignedProjectsEntity(
    val accepted_bid_id: String?,
    val additional_details: String?,
    val attachments_id: Int,
    val bidding_closed: Int,
    val bids_count: Int,
    val budget: String,
    val category_id: Int,
    val complexity: String?,
    val created_at: String,
    val currency_id: Int,
    val deadline: String,
    val description: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val manager_id: String?,
    val published: Int,
    val secondary_category_id: Int?,
    val skills: String,
    val slug: String?,
    val status: String,
    val subcategory_id: Int?,
    val tags: String,
    val title: String,
    val updated_at: String,
    val user_id: Int,
    val worker_id: String?
)