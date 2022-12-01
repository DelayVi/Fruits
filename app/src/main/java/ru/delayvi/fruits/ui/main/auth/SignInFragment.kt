package ru.delayvi.fruits.ui.main.auth

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.delayvi.fruits.R
import ru.delayvi.fruits.databinding.FragmentSignInBinding
import ru.delayvi.fruits.databinding.FragmentSplashBinding
import ru.delayvi.fruits.ui.splash.SplashViewModel
import java.lang.Exception

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private val viewModel by viewModels<SignInViewModel>()

    private var _binding: FragmentSignInBinding? = null
    private val binding: FragmentSignInBinding
        get() = _binding ?: throw RuntimeException("FragmentSignInBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.authException.observe(viewLifecycleOwner) {
            toastAuthException(it.toString())
        }

        viewModel.successAuth.observe(viewLifecycleOwner) {
            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToTabsFragment())
        }

        with(binding) {
            signInButton.setOnClickListener {
                viewModel.signIn(emailEditText.text.toString(), passwordEditText.text.toString())
            }

            signUpButton.setOnClickListener {
                navigateToSignUpDestination()
            }
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callback)
    }


    private fun navigateToSignUpDestination() {
        findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToSignUpFragment())
    }

    private fun toastAuthException(exception: String) {
        Toast.makeText(requireContext(), exception, Toast.LENGTH_SHORT).show()
    }

}