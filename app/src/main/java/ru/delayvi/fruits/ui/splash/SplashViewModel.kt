package ru.delayvi.fruits.ui.splash

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.delayvi.fruits.domain.accounts.usecases.isSignedInUseCase
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
   private val  isSignedInUseCase: isSignedInUseCase
): ViewModel() {

    private val _isSignedIn = MutableLiveData<Boolean?>()
    val isSignedIn: LiveData<Boolean?>
    get() = _isSignedIn

    init {
        viewModelScope.launch {
            _isSignedIn.value = isSignedInUseCase()
        }
    }

     suspend fun isUser(): Boolean {
      return  isSignedInUseCase()
    }

}