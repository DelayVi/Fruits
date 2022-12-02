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
) : ViewModel() {

    private val _authException = MutableLiveData<Unit>()
    val authException: LiveData<Unit>
        get() = _authException

    private val _successAuth = MutableLiveData<Unit>()
    val successAuth: LiveData<Unit>
        get() = _successAuth


    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                signInUseCase(email, password)
                _successAuth.value = Unit
            } catch (e: AuthException) {
                _authException.value = Unit
            }
        }
    }


}