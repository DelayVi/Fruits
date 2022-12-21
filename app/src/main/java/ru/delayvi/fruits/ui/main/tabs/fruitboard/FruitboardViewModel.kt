package ru.delayvi.fruits.ui.main.tabs.fruitboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.delayvi.fruits.domain.fruits.entity.Fruit
import ru.delayvi.fruits.domain.fruits.usecases.GetFruitsSettingsUseCase
import javax.inject.Inject

@HiltViewModel
class FruitboardViewModel @Inject constructor(
    private val getFruitsSettingsUseCase: GetFruitsSettingsUseCase,
) : ViewModel() {

    private val _fruits = MutableStateFlow<List<Fruit>>(listOf())
    val fruits = _fruits.asStateFlow()

    init {
        viewModelScope.launch {
            getFruitsSettingsUseCase(true).collect { list ->
                _fruits.value = list.map { it.fruit }
            }
        }
    }
}