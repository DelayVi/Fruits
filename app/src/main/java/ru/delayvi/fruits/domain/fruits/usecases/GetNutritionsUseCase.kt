package ru.delayvi.fruits.domain.fruits.usecases

import ru.delayvi.fruits.domain.fruits.FruitsRepository
import javax.inject.Inject

class GetNutritionsUseCase @Inject constructor(
    private val fruitsRepository: FruitsRepository
) {
    suspend operator fun invoke(fruitName: String) = fruitsRepository.getNutritions(fruitName)
}