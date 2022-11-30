package ru.delayvi.fruits.domain.accounts.usecases

import ru.delayvi.fruits.domain.accounts.AccountsRepository
import javax.inject.Inject

class UpdateUsernameUseCase @Inject constructor(
    private val accountsRepository: AccountsRepository
) {
    suspend operator fun invoke(newUsername: String) = accountsRepository.updateUsername(newUsername)
}