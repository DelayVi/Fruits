package ru.delayvi.fruits.ui.main.tabs.fruitboard.recycler_view

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

class FruitboardViewHolder @Inject constructor(
val binding: ViewDataBinding
): RecyclerView.ViewHolder(binding.root)