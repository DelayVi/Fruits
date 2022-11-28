package ru.delayvi.fruits.ui.main.tabs.fruitboard

import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import ru.delayvi.fruits.domain.fruits.FruitsRepository

class FruitViewModel @AssistedInject constructor(
@Assisted fruitId: Long,
private val fruitsRepository: FruitsRepository
) : ViewModel() {
    // TODO: Implement the ViewModel
}