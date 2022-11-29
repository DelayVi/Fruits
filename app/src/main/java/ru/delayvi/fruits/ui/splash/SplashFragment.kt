package ru.delayvi.fruits.ui.splash

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.delayvi.fruits.databinding.FragmentSplashBinding

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private val viewModel by viewModels<SplashViewModel>()

    private var _binding: FragmentSplashBinding? = null
    private val binding: FragmentSplashBinding
        get() = _binding ?: throw RuntimeException("FragmentSplashBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isSignedIn.observe(viewLifecycleOwner) {
            if (it != null) {
                launchNextDestination(it)
            } else launchNextDestination(false)
        }
    }


    private fun launchNextDestination(isSignedIn: Boolean) {

        if (isSignedIn) {
            viewModel.getAccount()
            viewModel.account.observe(viewLifecycleOwner) {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToTabsFragment(it))
            }
        } else {
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToSignInFragment())
        }
    }


    private fun toast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

}
