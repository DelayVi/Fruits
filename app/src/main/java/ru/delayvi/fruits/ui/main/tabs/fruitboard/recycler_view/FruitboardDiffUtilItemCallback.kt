package ru.delayvi.fruits.ui.main.tabs.fruitboard.recycler_view

import androidx.recyclerview.widget.DiffUtil
import ru.delayvi.fruits.domain.fruits.entity.Fruit

class FruitboardDiffUtilItemCallback : DiffUtil.ItemCallback<Fruit>() {
    override fun areItemsTheSame(oldItem: Fruit, newItem: Fruit): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Fruit, newItem: Fruit): Boolean {
        return oldItem == newItem
    }
}