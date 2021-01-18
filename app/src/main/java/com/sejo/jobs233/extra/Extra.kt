package com.sejo.jobs233.extra

import android.text.format.DateUtils
import com.sejo.jobs233.database.entity.ProfileEntity
import com.sejo.jobs233.database.entity.UserEntity
import com.sejo.jobs233.models.data.User
import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.util.*

fun String.clear(): String = ""

fun parseOffsetDateTime(dateString: String): CharSequence? {
    val odt = OffsetDateTime.parse(dateString)
    val odtInMillis = odt.toInstant().toEpochMilli()
    return DateUtils.getRelativeTimeSpanString(odtInMillis)
}

fun parseDeadlineDate(dateString: String): CharSequence? {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val deadlineDate = sdf.parse(dateString)!!.time
    return DateUtils.getRelativeTimeSpanString(deadlineDate)
}

fun User.asUserEntity(): UserEntity {
    return UserEntity(
        this.id,
        this.name,
        this.username,
        this.email,
        this.email_verified_at,
        this.created_at,
        this.updated_at,
        this.projects_count,
        this.profile.id
    )
}

fun User.asProfileEntity(): ProfileEntity {
    val prof = this.profile
    return ProfileEntity(
        prof.id,
        prof.user_id,
        prof.picture,
        prof.gender,
        prof.phone_number,
        prof.country,
        prof.city,
        prof.address,
        prof.bio,
        prof.preference,
        prof.is_occupied,
        prof.preferred_currency_id,
        prof.created_at,
        prof.updated_at
    )
}

