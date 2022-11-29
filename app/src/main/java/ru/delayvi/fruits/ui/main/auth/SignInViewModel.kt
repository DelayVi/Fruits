package ru.delayvi.fruits.ui.main.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.delayvi.fruits.domain.accounts.entity.SignUpData
import ru.delayvi.fruits.domain.accounts.usecases.GetAuthException
import ru.delayvi.fruits.domain.accounts.usecases.SignInUseCase
import ru.delayvi.fruits.domain.accounts.usecases.SignUpUseCase
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val getAuthException: GetAuthException
) : ViewModel() {

    private val _authException = getAuthException()
    val authException = _authException

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            signInUseCase(email, password)
        }
    }

}