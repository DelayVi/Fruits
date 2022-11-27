package ru.delayvi.fruits.domain.accounts.usecases

import androidx.lifecycle.LiveData
import ru.delayvi.fruits.domain.accounts.AccountsRepository

class GetAuthException(
    private val accountsRepository: AccountsRepository
) {
    operator fun invoke(): LiveData<Unit> = accountsRepository.getAuthException()
}