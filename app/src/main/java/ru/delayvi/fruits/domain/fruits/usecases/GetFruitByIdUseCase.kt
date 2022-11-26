package ru.delayvi.fruits.domain.fruits.usecases

import ru.delayvi.fruits.domain.fruits.FruitsRepository

class GetFruitByIdUseCase(
    private val fruitsRepository: FruitsRepository
) {
    suspend operator fun invoke(id: Long) = fruitsRepository.getFruitById(id)
}