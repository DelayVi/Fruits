package ru.delayvi.fruits.domain.fruits.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Nutritions(
    val carbohydrates: Double,
    val protein: Double,
    val fat: Double,
    val calories: Int,
    val sugar: Double
): Parcelable
