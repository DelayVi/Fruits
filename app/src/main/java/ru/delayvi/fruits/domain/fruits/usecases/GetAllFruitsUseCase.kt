package ru.delayvi.fruits.domain.fruits.usecases

import ru.delayvi.fruits.domain.fruits.FruitsRepository
import javax.inject.Inject

class GetAllFruitsUseCase @Inject constructor(
    private val fruitsRepository: FruitsRepository
) {
    suspend operator fun invoke() = fruitsRepository.getAllFruits()
}