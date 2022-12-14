package ru.delayvi.fruits.domain.fruits

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.delayvi.fruits.domain.fruits.entity.Fruit
import ru.delayvi.fruits.domain.fruits.entity.FruitSettings
import ru.delayvi.fruits.domain.fruits.entity.Nutritions

interface FruitsRepository {

    suspend fun getFruitsSettings(onlyActive: Boolean = false): Flow<List<FruitSettings>>

    suspend fun activateFruit(fruit: Fruit)

    suspend fun deactivateFruit(fruit: Fruit)

    suspend fun getNutritions(fruitName: String): Nutritions

}