package ru.delayvi.fruits.ui.main.tabs.fruitboard

import android.widget.TextView
import androidx.databinding.BindingAdapter

class FruitBindingAdapters {
    @BindingAdapter("setNutritionsToBottomSheet")
    fun setNutritionsToBottomSheet(textView: TextView, amount: Double) {
        textView.text = "${amount.toString()} г."
    }

    @BindingAdapter("setNutritionsToBottomSheet")
    fun setCaloriesToBottomSheet(textView: TextView, amount: Int) {
        textView.text = "${amount.toString()} ккал."
    }
}