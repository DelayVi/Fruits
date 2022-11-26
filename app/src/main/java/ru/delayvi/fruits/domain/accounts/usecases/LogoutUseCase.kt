package ru.delayvi.fruits.domain.accounts.usecases

import ru.delayvi.fruits.domain.accounts.AccountsRepository

class LogoutUseCase(
    private val accountsRepository: AccountsRepository
) {
    suspend operator fun invoke() = accountsRepository.logout()
}