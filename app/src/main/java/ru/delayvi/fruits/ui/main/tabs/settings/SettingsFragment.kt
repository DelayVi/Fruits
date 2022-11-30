package ru.delayvi.fruits.ui.main.tabs.settings

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.delayvi.fruits.R
import ru.delayvi.fruits.databinding.FragmentSettingsBinding
import ru.delayvi.fruits.databinding.FragmentTabsBinding
import ru.delayvi.fruits.domain.fruits.entity.Fruit
import ru.delayvi.fruits.domain.fruits.entity.FruitSettings
import ru.delayvi.fruits.ui.main.tabs.fruitboard.recycler_view.FruitboardAdapter
import ru.delayvi.fruits.ui.main.tabs.settings.recycler_view.SettingsAdapter
import ru.delayvi.fruits.ui.splash.SplashViewModel

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private val viewModel by viewModels<SplashViewModel>()

    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding
        get() = _binding ?: throw RuntimeException("FragmentSettingsBinding == null")

    private var settingsAdapter = SettingsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.settingsList.adapter = settingsAdapter

        val test = listOf(
            FruitSettings(Fruit(1, "Apple"), true),
            FruitSettings(Fruit(2, "Mango"), true),
            FruitSettings(Fruit(3, "Blackberry"), true),
            FruitSettings(Fruit(4, "Pineapple"), false),
            FruitSettings(Fruit(5, "Dragon"), true),
            FruitSettings(Fruit(6, "Blueberry"), true),
            FruitSettings(Fruit(7, "Raspberry"), true),
            FruitSettings(Fruit(8, "Cherry"), true),
            FruitSettings(Fruit(9, "Lichi"), false),
            FruitSettings(Fruit(10, "Orange"), true),
            FruitSettings(Fruit(11, "Lemon"), true),
            FruitSettings(Fruit(12, "Lime"), true)
        )
        settingsAdapter.submitList(test)
    }

}