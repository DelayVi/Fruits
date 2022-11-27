package ru.delayvi.fruits.data.settings

import android.app.Application
import android.content.Context
import ru.delayvi.fruits.data.settings.AppSettings.Companion.NO_LOGGED_IN_ACCOUNT_ID

class AppSettingsImpl(
    application: Application
) : AppSettings {

    private val sharedPreferences = application.getSharedPreferences("settings", Context.MODE_PRIVATE)

    override fun getCurrentAccountId(): Long =
        sharedPreferences.getLong(PREF_CURRENT_ACCOUNT_ID, NO_LOGGED_IN_ACCOUNT_ID)

    override fun setCurrentAccountId(id: Long) {
        sharedPreferences.edit()
            .putLong(PREF_CURRENT_ACCOUNT_ID, id)
            .apply()
    }

    companion object {
        private const val PREF_CURRENT_ACCOUNT_ID = "currentAccountId"
    }
}