package ru.delayvi.fruits.ui.main.tabs.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.delayvi.fruits.domain.fruits.entity.FruitSettings
import ru.delayvi.fruits.domain.fruits.usecases.ActivateFruitUseCase
import ru.delayvi.fruits.domain.fruits.usecases.DeactivateFruitUseCase
import ru.delayvi.fruits.domain.fruits.usecases.GetFruitsSettingsUseCase
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getFruitsSettingsUseCase: GetFruitsSettingsUseCase,
    private val activateFruitUseCase: ActivateFruitUseCase,
    private val deactivateFruitUseCase: DeactivateFruitUseCase
) : ViewModel() {

    private val _fruitSettings = MutableStateFlow<List<FruitSettings>>(listOf())
    val fruitSettings = _fruitSettings.asStateFlow()

    init {
        viewModelScope.launch {
            getFruitsSettingsUseCase().collect {
                _fruitSettings.value = it
            }
        }
    }

    fun changeFruitActivation(fruitSettings: FruitSettings) {
        viewModelScope
            .launch {
                if (fruitSettings.isActive) deactivateFruitUseCase(fruitSettings.fruit)
                else activateFruitUseCase(fruitSettings.fruit)
            }
    }

}