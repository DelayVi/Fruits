package ru.delayvi.fruits.ui.main.tabs

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ru.delayvi.fruits.domain.accounts.entity.Account
import ru.delayvi.fruits.domain.accounts.usecases.GetAccountUseCase
import javax.inject.Inject

@HiltViewModel
class TabsViewModel @Inject constructor(
    private val getAccountUseCase: GetAccountUseCase
) : ViewModel() {


    private val _account = MutableStateFlow<Account?>(null)
    val account: StateFlow<Account?> = _account.asStateFlow()

    fun getAccount() {
        viewModelScope.launch {
            val accountFlow = getAccountUseCase()
            accountFlow.first().let {
                if (it != null) {
                    _account.value = it
                }
            }
        }
    }

}

