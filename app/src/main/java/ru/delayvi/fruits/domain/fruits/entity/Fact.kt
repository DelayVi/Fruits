package ru.delayvi.fruits.domain.fruits.entity

data class Fact(
    val genus: String,
    val name: String,
    val id: Int,
    val family: String,
    val order: String,
    val nutritions: Nutritions
)
