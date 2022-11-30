package ru.delayvi.fruits.ui.main.tabs.settings.recycler_view

import androidx.recyclerview.widget.DiffUtil
import ru.delayvi.fruits.domain.fruits.entity.FruitSettings

class SettingsDiffUtilItemCallback : DiffUtil.ItemCallback<FruitSettings>() {
    override fun areItemsTheSame(oldItem: FruitSettings, newItem: FruitSettings): Boolean {
        return oldItem.fruit.id == newItem.fruit.id
    }

    override fun areContentsTheSame(oldItem: FruitSettings, newItem: FruitSettings): Boolean {
        return oldItem == newItem
    }

}