package ru.delayvi.fruits.ui.main.tabs.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.delayvi.fruits.domain.accounts.entity.Account
import ru.delayvi.fruits.domain.accounts.usecases.GetAccountUseCase
import ru.delayvi.fruits.domain.accounts.usecases.LogoutUseCase
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
private val logoutUseCase: LogoutUseCase,
private val getAccountUseCase: GetAccountUseCase
) : ViewModel() {


    private val _currentAccount = MutableLiveData<Account>()
    val currentAccount: LiveData<Account>
        get() = _currentAccount

    init {
        viewModelScope.launch {
            getAccountUseCase().collect() {
                _currentAccount.value = it
            }
        }
    }

    fun logout() {
        viewModelScope.launch { logoutUseCase() }
    }
}