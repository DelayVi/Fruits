package ru.delayvi.fruits.data.impl

import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import dagger.hilt.components.SingletonComponent
import it.czerwinski.android.hilt.annotations.Bound
import it.czerwinski.android.hilt.annotations.BoundTo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.delayvi.fruits.data.database.accounts.AccountDbEntity
import ru.delayvi.fruits.data.database.accounts.AccountsDao
import ru.delayvi.fruits.data.database.accounts.UpdateUsernameTuple
import ru.delayvi.fruits.data.settings.AppSettings
import ru.delayvi.fruits.data.settings.AppSettings.Companion.NO_LOGGED_IN_ACCOUNT_ID
import ru.delayvi.fruits.domain.accounts.AccountAlreadyExistException
import ru.delayvi.fruits.domain.accounts.AccountsRepository
import ru.delayvi.fruits.domain.accounts.AuthException
import ru.delayvi.fruits.domain.accounts.entity.Account
import ru.delayvi.fruits.domain.accounts.entity.SignUpData
import java.lang.Exception
import javax.inject.Inject

@BoundTo(supertype = AccountsRepository::class, component = SingletonComponent::class)
class AccountsRepositoryImpl @Inject constructor(
    private val accountsDao: AccountsDao,
    private val appSettings: AppSettings
) : AccountsRepository {

    private val authException = MutableLiveData<String>()

    override suspend fun isSignedIn(): Boolean {
        delay(2000)
        return appSettings.getCurrentAccountId() != NO_LOGGED_IN_ACCOUNT_ID
    }

    override suspend fun signIn(email: String, password: String) {
        delay(1000)
        try {
            accountsDao.findByEmail(email).let {
                if (it == null) throw AuthException()
                    if (it.password != password) throw AuthException()
                    else appSettings.setCurrentAccountId(it.id)
            }
        }catch (e: SQLiteConstraintException) {
            val exception = AuthException()
            exception.initCause(e)
            throw exception
        }

    }

    override suspend fun signUp(signUpData: SignUpData) {
        delay(1000)
        try {
            val entity = AccountDbEntity.fromSignUpData(signUpData)
            accountsDao.createAccount(entity)
        } catch (e: SQLiteConstraintException) {
            val exception = AccountAlreadyExistException()
            exception.initCause(e)
            throw exception
        }
    }

    override suspend fun logout() {
        appSettings.setCurrentAccountId(NO_LOGGED_IN_ACCOUNT_ID)
    }

    override suspend fun getAccount(): Flow<Account?> {
        val accountId = appSettings.getCurrentAccountId()
        return accountsDao.getById(accountId).map { it?.toAccount() }
    }

    override suspend fun updateUsername(newUsername: String) {
        delay(1500)
        val accountId = appSettings.getCurrentAccountId()
        accountsDao.updateUsername(UpdateUsernameTuple(accountId, newUsername))
    }



}