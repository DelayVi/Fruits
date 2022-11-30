package ru.delayvi.fruits.domain.fruits.usecases

import ru.delayvi.fruits.domain.fruits.FruitsRepository
import javax.inject.Inject

class GetFruitsSettingsUseCase @Inject constructor(
    private val fruitsRepository: FruitsRepository
) {
    suspend operator fun invoke(onlyActive: Boolean = false) = fruitsRepository.getFruitsSettings(onlyActive)
}