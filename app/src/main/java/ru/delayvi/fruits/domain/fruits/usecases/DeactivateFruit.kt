package ru.delayvi.fruits.domain.fruits.usecases

import ru.delayvi.fruits.domain.fruits.entity.Fruit
import ru.delayvi.fruits.domain.fruits.FruitsRepository

class DeactivateFruit(
    private val fruitsRepository: FruitsRepository
) {
    suspend operator fun invoke(fruit: Fruit) = fruitsRepository.deactivateFruit(fruit)
}