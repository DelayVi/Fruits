package ru.delayvi.fruits.ui.main.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ru.delayvi.fruits.domain.accounts.AuthException
import ru.delayvi.fruits.domain.accounts.entity.Account
import ru.delayvi.fruits.domain.accounts.usecases.GetAccountUseCase
import ru.delayvi.fruits.domain.accounts.usecases.SignInUseCase
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val getAccountUseCase: GetAccountUseCase
) : ViewModel() {

    private val _authException = MutableLiveData<Unit>()
    val authException: LiveData<Unit>
        get() = _authException

    private val _account = MutableLiveData<Account>()
    val account: LiveData<Account>
        get() = _account

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                signInUseCase(email, password)
                getAccount()
            } catch (e: AuthException) {
                _authException.value = Unit
            }
        }
    }

    fun getAccount() {
        viewModelScope.async {
            val accountFlow = getAccountUseCase()
            _account.value = accountFlow.first()
            return@async accountFlow.first()
        }
    }

}