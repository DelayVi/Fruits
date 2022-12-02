package ru.delayvi.fruits.domain.fruits.usecases

import ru.delayvi.fruits.domain.fruits.entity.Fruit
import ru.delayvi.fruits.domain.fruits.FruitsRepository
import javax.inject.Inject

class ActivateFruitUseCase @Inject constructor(
    private val fruitsRepository: FruitsRepository
) {
    suspend operator fun invoke(fruit: Fruit) = fruitsRepository.activateFruit(fruit)
}