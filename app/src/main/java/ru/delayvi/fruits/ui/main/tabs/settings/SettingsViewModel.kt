package ru.delayvi.fruits.ui.main.tabs.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.delayvi.fruits.domain.accounts.entity.Account
import ru.delayvi.fruits.domain.accounts.usecases.GetAccountUseCase
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(

) : ViewModel() {


}