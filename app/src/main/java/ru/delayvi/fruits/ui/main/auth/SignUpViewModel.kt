package ru.delayvi.fruits.ui.main.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.delayvi.fruits.domain.accounts.AccountAlreadyExistException
import ru.delayvi.fruits.domain.accounts.entity.SignUpData
import ru.delayvi.fruits.domain.accounts.usecases.SignUpUseCase
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    ) : ViewModel() {

    private val _signUpException = MutableLiveData<String>()
    val signUpException: LiveData<String>
        get() = _signUpException

    private val _isBlankFieldException = MutableLiveData<Unit>()
    val isBlankFieldException: LiveData<Unit>
        get() = _isBlankFieldException

    private val _passwordsEqualsException = MutableLiveData<Unit>()
    val passwordsEqualsException: LiveData<Unit>
        get() = _passwordsEqualsException

    private val _readyToBackInSignInFragment = MutableLiveData<Unit>()
    val readyToBackInSignInFragment: LiveData<Unit>
        get() = _readyToBackInSignInFragment

    fun signUp(signUpData: SignUpData) {
        viewModelScope.launch {
            try{
                if (fieldValidation(signUpData)) {
                    signUpUseCase(signUpData)
                    _readyToBackInSignInFragment.value = Unit
                }
            }catch (e:AccountAlreadyExistException) {
                _signUpException.value = "Account already exist"
            }
        }
    }

    private fun fieldValidation(
        signUpData: SignUpData
    ): Boolean {
        with(signUpData) {
            if (email.isBlank()) throwIsBlankFieldException()
            else if (username.isBlank()) throwIsBlankFieldException()
            else if (password.isBlank()) throwIsBlankFieldException()
            else if (confirmPassword.isBlank()) throwIsBlankFieldException()
            else if (password != confirmPassword) throwPasswordsNotEquals()
            else return true
            return false
        }
    }

    private fun throwIsBlankFieldException() {
        _isBlankFieldException.value = Unit
    }

    private fun throwPasswordsNotEquals() {
        _passwordsEqualsException.value = Unit
    }


}