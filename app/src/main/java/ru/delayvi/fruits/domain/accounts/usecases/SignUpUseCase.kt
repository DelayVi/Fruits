package ru.delayvi.fruits.domain.accounts.usecases

import ru.delayvi.fruits.domain.accounts.AccountsRepository
import ru.delayvi.fruits.domain.accounts.entity.SignUpData

class SignUpUseCase(
    private val accountsRepository: AccountsRepository
) {
    suspend operator fun invoke(signUpData: SignUpData) = accountsRepository.signUp(signUpData)
}