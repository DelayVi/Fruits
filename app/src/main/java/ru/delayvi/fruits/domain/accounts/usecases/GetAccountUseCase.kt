package ru.delayvi.fruits.domain.accounts.usecases

import ru.delayvi.fruits.domain.accounts.AccountsRepository

class GetAccountUseCase(
    private val accountsRepository: AccountsRepository
) {
    suspend operator fun invoke() = accountsRepository.getAccount()
}