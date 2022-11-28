package ru.delayvi.fruits.ui.splash

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import ru.delayvi.fruits.R
import ru.delayvi.fruits.databinding.FragmentSplashBinding
import ru.delayvi.fruits.ui.main.MainActivity
import ru.delayvi.fruits.ui.main.MainActivityArgs

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

        viewModel.isSignedIn.observe(viewLifecycleOwner){
            if (it != null) {
                launchMainActivity(it)
            }
            else launchMainActivity(false)
        }
    }

    private fun launchMainActivity(isSignedIn: Boolean) {
        val intent = Intent(requireContext(), MainActivity::class.java)

        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToMainActivity(isSignedIn))
        //startActivity(intent)
    }


    private fun toast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

}