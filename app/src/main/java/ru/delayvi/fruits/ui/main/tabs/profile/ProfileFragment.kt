package ru.delayvi.fruits.ui.main.tabs.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.delayvi.fruits.R
import ru.delayvi.fruits.databinding.FragmentProfileBinding
import ru.delayvi.fruits.databinding.FragmentSettingsBinding
import ru.delayvi.fruits.ui.main.tabs.TabsFragment
import ru.delayvi.fruits.ui.main.tabs.TabsFragmentDirections
import ru.delayvi.fruits.ui.splash.SplashViewModel

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val viewModel by viewModels<ProfileViewModel>()

    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding
        get() = _binding ?: throw RuntimeException("FragmentProfileBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.logoutButton.setOnClickListener {
            viewModel.logout()
            logout()

        }
    }

    private fun logout() {
        val topLevelHost =
            requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment?
        topLevelHost?.navController?.navigate(R.id.signInFragment)

    }
}