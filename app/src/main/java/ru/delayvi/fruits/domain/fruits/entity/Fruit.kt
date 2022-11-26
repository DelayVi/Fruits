package ru.delayvi.fruits.domain.fruits.entity

import android.os.Parcelable

data class Fruit(
    val id: Long,
    val fruitName: String,
    val fact: Fact?
)
