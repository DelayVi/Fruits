package ru.delayvi.fruits.ui.main.tabs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.delayvi.fruits.R
import ru.delayvi.fruits.databinding.FragmentTabsBinding

@AndroidEntryPoint
class TabsFragment : Fragment() {
    private val viewModel by viewModels<TabsViewModel>()

    private var _binding: FragmentTabsBinding? = null
    private val binding: FragmentTabsBinding
        get() = _binding ?: throw RuntimeException("FragmentTabsBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupWithNavController()

        viewModel.account
            .onEach {
                viewModel.getAccount()
                binding.usernameTextView.text = "@${it?.username}"
            }
            .launchIn(lifecycleScope)
    }

    private fun setupWithNavController() {
        val navHost = childFragmentManager.findFragmentById(R.id.tabsContainer) as NavHostFragment
        val navController = navHost.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
        navController.addOnDestinationChangedListener { _, _, _ ->
            binding.toolbar.title = navController.currentDestination?.label
        }
    }

}