package ru.delayvi.fruits.data.settings

import android.app.Application
import android.content.Context
import dagger.hilt.components.SingletonComponent
import it.czerwinski.android.hilt.annotations.BoundTo
import ru.delayvi.fruits.data.settings.AppSettings.Companion.NO_LOGGED_IN_ACCOUNT_ID
import javax.inject.Inject

@BoundTo(supertype = AppSettings::class, component = SingletonComponent::class)
class AppSettingsImpl @Inject constructor(
    application: Application
) : AppSettings {

    private val sharedPreferences = application.getSharedPreferences("settings", Context.MODE_PRIVATE)

    override fun getCurrentAccountId(): Long =
        sharedPreferences.getLong(PREF_CURRENT_ACCOUNT_ID, NO_LOGGED_IN_ACCOUNT_ID)

    override fun setCurrentAccountId(accountId: Long) {
        sharedPreferences.edit()
            .putLong(PREF_CURRENT_ACCOUNT_ID, accountId)
            .apply()
    }

    companion object {
        private const val PREF_CURRENT_ACCOUNT_ID = "currentAccountId"
    }
}