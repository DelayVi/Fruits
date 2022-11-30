package ru.delayvi.fruits.ui.main.tabs.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.delayvi.fruits.domain.accounts.AccountsExceptions
import ru.delayvi.fruits.domain.accounts.usecases.UpdateUsernameUseCase
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class EditUsernameViewModel @Inject constructor(
    private val updateUsernameUseCase: UpdateUsernameUseCase
) : ViewModel() {

    private val _showProgressBar = MutableLiveData<Boolean>()
    val showProgressBar: LiveData<Boolean>
        get() = _showProgressBar

    private val _finishScreenListener = MutableLiveData<Unit>()
    val finishScreenListener: LiveData<Unit>
        get() = _finishScreenListener

    fun updateUsername(newUsername: String) {
        viewModelScope.launch {
            try {
                _showProgressBar.value = true
                updateUsernameUseCase(newUsername)
                _showProgressBar.value = false
                _finishScreenListener.value = Unit
            }catch (e:AccountsExceptions){
                e.message
            }
        }
    }
}