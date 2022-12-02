package ru.delayvi.fruits.data.network

import ru.delayvi.fruits.data.database.fruits.FruitDbEntity
import ru.delayvi.fruits.domain.fruits.entity.Fruit

data class FruitNetworkEntity(
 val genus: String,
 val name: String,
 val id: Long,
 val family: String,
 val order: String,
 val nutritions: NutritionsNetworkEntity
) {
    fun toFruit() = Fruit(
        id = id,
        fruitName = name
    )

    fun toFruitDbEntity() = FruitDbEntity(
        id = id,
        fruitName = name
    )
}
