package ru.delayvi.fruits.domain.accounts.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Account(
    val id: Long,
    val username: String,
    val email: String,
    val createdAt: Long = UNKNOWN_CREATED_AT
): Parcelable {
    companion object {
        const val UNKNOWN_CREATED_AT = 0L
    }
}
