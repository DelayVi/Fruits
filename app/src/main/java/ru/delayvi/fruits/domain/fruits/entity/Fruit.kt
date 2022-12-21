package ru.delayvi.fruits.domain.fruits.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Fruit(
    val id: Long,
    val fruitName: String,
): Parcelable
