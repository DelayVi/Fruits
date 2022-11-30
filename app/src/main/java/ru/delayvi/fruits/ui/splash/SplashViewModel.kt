package ru.delayvi.fruits.ui.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import ru.delayvi.fruits.domain.accounts.entity.Account
import ru.delayvi.fruits.domain.accounts.usecases.GetAccountUseCase
import ru.delayvi.fruits.domain.accounts.usecases.isSignedInUseCase
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
   private val  isSignedInUseCase: isSignedInUseCase,
   private val getAccountUseCase: GetAccountUseCase
): ViewModel() {

    private val _isSignedIn = MutableLiveData<Boolean?>()
    val isSignedIn: LiveData<Boolean?>
    get() = _isSignedIn

    private val _account = MutableLiveData<Account>()
    val account: LiveData<Account>
    get() = _account

    init {
        viewModelScope.launch {
            _isSignedIn.value = isSignedInUseCase()
        }
    }

    fun getAccount() {
        viewModelScope.async {
            val accountFlow = getAccountUseCase()
            _account.value = accountFlow.first()
            return@async accountFlow.first()
        }
    }

     suspend fun isUser(): Boolean {
      return  isSignedInUseCase()
    }

}