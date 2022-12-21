package ru.delayvi.fruits.data.network.entity

import ru.delayvi.fruits.data.database.fruits.FruitDbEntity
import ru.delayvi.fruits.domain.fruits.entity.Fruit
import ru.delayvi.fruits.domain.fruits.entity.Nutritions

data class FruitNetworkEntity(
    val genus: String,
    val name: String,
    val id: Long,
    val family: String,
    val order: String,
    val nutritions: NutritionsNetworkEntity
) {
    fun toNutritions() = with(nutritions) {
        Nutritions(
            carbohydrates = carbohydrates,
            protein = protein,
            fat = fat,
            calories = calories,
            sugar = sugar
        )
    }


    fun toFruitDbEntity() = FruitDbEntity(
        id = id,
        fruitName = name
    )
}
