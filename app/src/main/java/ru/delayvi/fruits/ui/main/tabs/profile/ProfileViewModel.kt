package ru.delayvi.fruits.ui.main.tabs.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.delayvi.fruits.domain.accounts.usecases.LogoutUseCase
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    fun logout() {
        viewModelScope.launch { logoutUseCase() }
    }
}