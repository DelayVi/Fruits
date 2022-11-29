package ru.delayvi.fruits.ui.main.tabs

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import ru.delayvi.fruits.R
import ru.delayvi.fruits.databinding.FragmentSplashBinding
import ru.delayvi.fruits.databinding.FragmentTabsBinding
import ru.delayvi.fruits.ui.splash.SplashViewModel

@AndroidEntryPoint
class TabsFragment : Fragment() {

    private val args by navArgs<TabsFragmentArgs>()

    private val viewModel by viewModels<SplashViewModel>()

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

        binding.usernameTextView.text = "@${args.account.username}"
        val navHost = childFragmentManager.findFragmentById(R.id.tabsContainer) as NavHostFragment
        val navController = navHost.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
    }

}