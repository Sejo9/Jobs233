package com.sejo.jobs233.extra

import android.text.format.DateUtils
import com.sejo.jobs233.database.entity.CurrencyEntity
import com.sejo.jobs233.database.entity.ProfileEntity
import com.sejo.jobs233.database.entity.UserEntity
import com.sejo.jobs233.database.entity.WalletEntity
import com.sejo.jobs233.models.data.user.User
import java.time.OffsetDateTime

fun String.clear(): String = ""

fun parseOffsetDateTime(dateString: String): CharSequence? {
    val odt = OffsetDateTime.parse(dateString)
    val odtInMillis = odt.toInstant().toEpochMilli()
    return DateUtils.getRelativeTimeSpanString(odtInMillis)
}

fun User.asUserEntity(): UserEntity {
    return UserEntity(
        this.id,
        this.name,
        this.first_name,
        this.last_name,
        this.username,
        this.email,
        this.email_verified_at,
        this.created_at,
        this.updated_at,
        this.is_online,
        this.projects_count,
        this.assigned_projects_to_worker_count,
        this.my_assigned_projects_count,
        this.completed_projects_count,
        this.completed_assigned_projects_count,
        this.profile.id,
        this.wallet.id
    )
}

fun User.asProfileEntity(): ProfileEntity {
    val prof = this.profile
    return ProfileEntity(
        prof.id,
        prof.user_id,
        prof.picture,
        prof.title,
        prof.gender,
        prof.phone_number,
        prof.country,
        prof.city,
        prof.address,
        prof.bio,
        prof.preference,
        prof.is_occupied,
        prof.preferred_currency_id,
        prof.can_assign_project_directly_to_worker,
        prof.created_at,
        prof.updated_at,
        prof.cover_image
    )
}

fun User.asWalletEntity(): WalletEntity {
    val wallet = this.wallet
    return WalletEntity(
        wallet.id,
        wallet.holder_type,
        wallet.holder_id,
        wallet.name,
        wallet.slug,
        wallet.description,
        //wallet.meta,
        wallet.balance,
        wallet.decimal_places,
        wallet.created_at,
        wallet.updated_at,
    )
}

/*fun User.asSkillsEntity(): SkillsEntity {
    return SkillsEntity(
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
}*/

fun User.asCurrencyEntity(): CurrencyEntity {
    val currency = this.profile.currency
    return CurrencyEntity(
        currency.id,
        currency.name,
        currency.exchange_rate_in_usd,
        currency.symbol,
        currency.created_at,
        currency.updated_at
    )
}



