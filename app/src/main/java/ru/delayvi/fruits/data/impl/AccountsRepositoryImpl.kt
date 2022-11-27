package ru.delayvi.fruits.data.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.delayvi.fruits.data.database.accounts.AccountDbEntity
import ru.delayvi.fruits.data.database.accounts.AccountsDao
import ru.delayvi.fruits.data.database.accounts.UpdateUsernameTuple
import ru.delayvi.fruits.data.settings.AppSettings
import ru.delayvi.fruits.data.settings.AppSettings.Companion.NO_LOGGED_IN_ACCOUNT_ID
import ru.delayvi.fruits.domain.accounts.AccountsRepository
import ru.delayvi.fruits.domain.accounts.entity.Account
import ru.delayvi.fruits.domain.accounts.entity.SignUpData
import java.lang.Exception

@Suppress("UNREACHABLE_CODE")
class AccountsRepositoryImpl(
    private val accountsDao: AccountsDao,
    private val appSettings: AppSettings
) : AccountsRepository {

    private val authException = MutableLiveData<Unit>()

    override suspend fun isSignedIn(): Boolean {
        delay(1000)
        return appSettings.getCurrentAccountId() != NO_LOGGED_IN_ACCOUNT_ID
    }

    override suspend fun signIn(email: String, password: String) {
        delay(1000)
        val accountId = findAccountIdByEmailAndPassword(email, password)
        if (accountId != NO_LOGGED_IN_ACCOUNT_ID) appSettings.setCurrentAccountId(accountId)
    }

    override suspend fun signUp(signUpData: SignUpData) {
        delay(1000)
        if (signUpData.password == signUpData.confirmPassword) {
            val entity = AccountDbEntity.fromSignUpData(signUpData)
            accountsDao.createAccount(entity)
        } else authException.value = Unit
    }

    override suspend fun logout() {
        delay(1000)
        appSettings.setCurrentAccountId(NO_LOGGED_IN_ACCOUNT_ID)
    }

    override suspend fun getAccount(): Flow<Account?> {
        delay(1000)
        val accountId = appSettings.getCurrentAccountId()
        return accountsDao.getById(accountId).map { it?.toAccount() }
    }

    override suspend fun updateUsername(newUsername: String) {
        delay(1000)
        val accountId = appSettings.getCurrentAccountId()
        accountsDao.updateUsername(UpdateUsernameTuple(accountId, newUsername))
    }

    override fun getAuthException(): LiveData<Unit> = authException

    private suspend fun findAccountIdByEmailAndPassword(email: String, password: String): Long {
        accountsDao.findByEmail(email).let {
            if (it.password != password) authException.value = Unit
            return it.id
        }
        authException.value = Unit
        return NO_LOGGED_IN_ACCOUNT_ID
    }
}