package ru.delayvi.fruits.ui.main.tabs.fruitboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.delayvi.fruits.domain.fruits.FruitsRepository
import ru.delayvi.fruits.domain.fruits.entity.Nutritions
import ru.delayvi.fruits.domain.fruits.usecases.GetNutritionsUseCase
import javax.inject.Inject

@HiltViewModel
class FruitViewModel @Inject constructor(
    private val getNutritionsUseCase: GetNutritionsUseCase
) : ViewModel() {

    private val _nutritions = MutableStateFlow<Nutritions?>(null)
    val nutritions = _nutritions.asStateFlow()

    fun getNutritions(fruitName: String) = viewModelScope.launch {
        _nutritions.value = getNutritionsUseCase(fruitName) }
}