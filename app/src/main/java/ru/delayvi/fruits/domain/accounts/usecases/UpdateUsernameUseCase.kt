package ru.delayvi.fruits.domain.accounts.usecases

import ru.delayvi.fruits.domain.accounts.AccountsRepository

class UpdateUsernameUseCase(
    private val accountsRepository: AccountsRepository
) {
    suspend operator fun invoke(newUsername: String) = accountsRepository.updateUsername(newUsername)
}