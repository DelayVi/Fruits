package ru.delayvi.fruits.ui.main.tabs.fruitboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.delayvi.fruits.domain.fruits.entity.Fruit
import ru.delayvi.fruits.domain.fruits.entity.FruitSettings
import ru.delayvi.fruits.domain.fruits.usecases.GetFruitsSettingsUseCase
import javax.inject.Inject

@HiltViewModel
class FruitboardViewModel @Inject constructor(
    private val getFruitsSettingsUseCase: GetFruitsSettingsUseCase
) : ViewModel() {

    private val _fruits = MutableLiveData<List<Fruit>>()
    val fruits: LiveData<List<Fruit>>
        get() = _fruits

    init {
        viewModelScope.launch {
            getFruitsSettingsUseCase(true).collect() { list ->
                _fruits.value = list.map { it.fruit }
            }
        }
    }

    fun testAddFruits() {
        val test = listOf<Fruit>(
            Fruit(1, "apple"),
            Fruit(2,"Mango"),
            Fruit(3, "blackberry")
        )

        _fruits.value = test
    }
}