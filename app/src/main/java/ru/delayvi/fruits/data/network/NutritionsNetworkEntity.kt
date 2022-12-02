package ru.delayvi.fruits.data.network

import ru.delayvi.fruits.domain.fruits.entity.Nutritions

data class NutritionsNetworkEntity(
    val carbohydrates: Double,
    val protein: Double,
    val fat: Double,
    val calories: Int,
    val sugar: Double
) {
    fun toNutritions() = Nutritions(
        carbohydrates = carbohydrates,
        protein = protein,
        fat = fat,
        calories = calories,
        sugar = sugar
    )
}
