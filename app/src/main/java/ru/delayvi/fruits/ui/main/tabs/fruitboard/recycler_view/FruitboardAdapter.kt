package ru.delayvi.fruits.ui.main.tabs.fruitboard.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.delayvi.fruits.databinding.ItemFruitBinding
import ru.delayvi.fruits.domain.fruits.entity.Fruit

class FruitboardAdapter() : ListAdapter<Fruit, FruitboardViewHolder>(FruitboardDiffUtilItemCallback()) {

    var onClickListener: ((Fruit) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitboardViewHolder {
        val binding = ItemFruitBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FruitboardViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(viewHolder: FruitboardViewHolder, position: Int) {
        val selectedFruit = getItem(position)
        with(viewHolder) {
            if (binding is ItemFruitBinding) {
                binding.fruit = selectedFruit
            }
            itemView.setOnClickListener { onClickListener?.invoke(selectedFruit) }
        }
    }
}