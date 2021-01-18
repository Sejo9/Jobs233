package com.sejo.jobs233.models.data.project

import com.sejo.jobs233.models.data.Currency
import com.sejo.jobs233.models.data.User

data class Project(
    val accepted_bid_id: String?,
    val additional_details: String?,
    val attachments: List<Attachment>,
    val bidding_closed: Int,
    val bids_count: Int,
    val budget: String,
    val category: Category,
    val category_id: Int,
    val complexity: String?,
    val created_at: String,
    val currency: Currency,
    val currency_id: String,
    val deadline: String,
    val description: String,
    val id: Int,
    val manager_id: String?,
    val published: Int,
    val secondary_category_id: Int?,
    val skills: String,
    val slug: String?,
    val status: String,
    val subcategory: SubCategory?,
    val tags: String,
    val title: String,
    val updated_at: String,
    val user: User,
    val user_id: Int,
    val worker_id: String?
)