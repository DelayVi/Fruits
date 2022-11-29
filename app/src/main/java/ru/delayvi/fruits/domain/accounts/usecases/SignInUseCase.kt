package ru.delayvi.fruits.domain.accounts.usecases

import ru.delayvi.fruits.domain.accounts.AccountsRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val accountsRepository: AccountsRepository
) {
    suspend operator fun invoke(email: String, password: String) = accountsRepository.signIn(email, password)
}