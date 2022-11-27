package ru.delayvi.fruits.data.settings

import ru.delayvi.fruits.domain.accounts.entity.Account

interface AppSettings {

    fun getCurrentAccountId(): Long

    fun setCurrentAccountId(accountId: Long)

    companion object {

        const val NO_LOGGED_IN_ACCOUNT_ID = -1L
    }
}