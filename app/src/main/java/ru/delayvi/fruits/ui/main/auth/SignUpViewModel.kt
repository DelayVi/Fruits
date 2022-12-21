package ru.delayvi.fruits.ui.main.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.delayvi.fruits.domain.accounts.AccountAlreadyExistException
import ru.delayvi.fruits.domain.accounts.AccountsExceptions
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

    private val _invalidEmailException = MutableLiveData<Unit>()
    val invalidEmailException: LiveData<Unit>
        get() = _invalidEmailException

    private val _passwordsEqualsException = MutableLiveData<Unit>()
    val passwordsEqualsException: LiveData<Unit>
        get() = _passwordsEqualsException

    private val _readyToBackInSignInFragment = MutableLiveData<Unit>()
    val readyToBackInSignInFragment: LiveData<Unit>
        get() = _readyToBackInSignInFragment

    private val _showProgressBar = MutableLiveData<Boolean>()
        .apply { value = false }
    val showProgressBar: LiveData<Boolean>
        get() = _showProgressBar

    fun signUp(signUpData: SignUpData) {
        viewModelScope.launch {
            try {
                _showProgressBar.value = true
                if (fieldValidation(signUpData)) {
                    signUpUseCase(signUpData)
                    _readyToBackInSignInFragment.value = Unit
                    _showProgressBar.value = false
                }
            } catch (e: AccountAlreadyExistException) {
                _showProgressBar.value = false
                _signUpException.value = "Account already exist"
            }
        }
    }

    private fun fieldValidation(
        signUpData: SignUpData
    ): Boolean {
        with(signUpData) {
            if (!validateEmail(email)) throwInvalidEmailException()
            else if (email.isBlank()) throwIsBlankFieldException()
            else if (username.isBlank()) throwIsBlankFieldException()
            else if (password.isBlank()) throwIsBlankFieldException()
            else if (confirmPassword.isBlank()) throwIsBlankFieldException()
            else if (password != confirmPassword) throwPasswordsNotEquals()
            else return true
            return false
        }
    }

    private fun validateEmail(email: String): Boolean {
        for (i in 1 until email.length) {
            if (email[i].toString() == "@") {
                for (j in i + 1 until email.length - 1) {
                    if (email[j].toString() == ".") {
                        return true
                    }
                }
            }
        }
        return false
    }

    private fun throwInvalidEmailException() {
        _invalidEmailException.value = Unit
        _showProgressBar.value = false
    }

    private fun throwIsBlankFieldException() {
        _isBlankFieldException.value = Unit
        _showProgressBar.value = false
    }

    private fun throwPasswordsNotEquals() {
        _passwordsEqualsException.value = Unit
        _showProgressBar.value = false
    }


}