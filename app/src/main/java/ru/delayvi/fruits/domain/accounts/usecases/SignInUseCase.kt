package ru.delayvi.fruits.domain.accounts.usecases

import ru.delayvi.fruits.domain.accounts.AccountsRepository

class SignInUseCase(
    private val accountsRepository: AccountsRepository
) {
    suspend operator fun invoke(email: String, password: String) = accountsRepository.signIn(email, password)
}