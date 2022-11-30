package ru.delayvi.fruits.ui.main.tabs.settings.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.delayvi.fruits.databinding.ItemSettingsBinding
import ru.delayvi.fruits.domain.fruits.entity.FruitSettings

class SettingsAdapter: ListAdapter<FruitSettings, SettingsViewHolder>(SettingsDiffUtilItemCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val binding = ItemSettingsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SettingsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(viewHolder: SettingsViewHolder, position: Int) {
        val selectedCheckBox = getItem(position)
        with(viewHolder) {
            if (binding is ItemSettingsBinding) {
                binding.settingsCheckBox.text = selectedCheckBox.fruit.fruitName
                binding.settingsCheckBox.isChecked = selectedCheckBox.isActive
                binding.settingsCheckBox.tag = selectedCheckBox
            }
        }
    }


}