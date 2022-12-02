package ru.delayvi.fruits.ui.main.tabs.settings

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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

    private val viewModel by viewModels<SettingsViewModel>()

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

        viewModel.fruitSettings.onEach {
            settingsAdapter.submitList(it)
        }
            .launchIn(lifecycleScope)

        settingsAdapter.onClickListener = {
            viewModel.changeFruitActivation(it)
        }
    }

}