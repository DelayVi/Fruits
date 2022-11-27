package ru.delayvi.fruits.domain.fruits.usecases

import ru.delayvi.fruits.domain.fruits.FruitsRepository

class GetFruitsSettingsUseCase(
    private val fruitsRepository: FruitsRepository
) {
    suspend operator fun invoke(onlyActive: Boolean = false) = fruitsRepository.getFruitsSettings(onlyActive)
}