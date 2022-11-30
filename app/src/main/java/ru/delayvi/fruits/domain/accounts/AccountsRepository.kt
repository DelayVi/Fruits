package ru.delayvi.fruits.domain.accounts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow
import ru.delayvi.fruits.domain.accounts.entity.Account
import ru.delayvi.fruits.domain.accounts.entity.SignUpData

interface AccountsRepository {

    suspend fun isSignedIn(): Boolean

    suspend fun signIn(email: String, password: String)

    suspend fun signUp(signUpData: SignUpData)

    suspend fun logout()

    suspend fun getAccount(): Flow<Account?>

    suspend fun updateUsername(newUsername: String)
}