package ru.delayvi.fruits.domain.accounts.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.delayvi.fruits.domain.accounts.AccountsRepository
import javax.inject.Inject

class GetAuthException @Inject constructor(
    private val accountsRepository: AccountsRepository
) {
    operator fun invoke(): MutableLiveData<String> = accountsRepository.getAuthException()
}