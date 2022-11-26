package ru.delayvi.fruits.domain.accounts.usecases

import ru.delayvi.fruits.domain.accounts.AccountsRepository

class isSignedInUseCase(
    private val accountsRepository: AccountsRepository
) {
    suspend operator fun invoke() = accountsRepository.isSignedIn()
}