package ru.delayvi.fruits.ui.main.auth

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.delayvi.fruits.R
import ru.delayvi.fruits.databinding.FragmentSignInBinding
import ru.delayvi.fruits.databinding.FragmentSignUpBinding
import ru.delayvi.fruits.domain.accounts.entity.SignUpData

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private val viewModel by viewModels<SignUpViewModel>()

    private var _binding: FragmentSignUpBinding? = null
    private val binding: FragmentSignUpBinding
        get() = _binding ?: throw RuntimeException("FragmentSignUpBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launchObservers()

        binding.createAccountButton.setOnClickListener {
            with(binding) {
                val username = usernameEditText.text.toString()
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()
                val confirmPassword = repeatPasswordEditText.text.toString()
                viewModel.signUp(SignUpData(username, email, password, confirmPassword))
            }
        }

    }

    private fun launchObservers(){
        viewModel.isBlankFieldException.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), getString(R.string.fields_is_blank), Toast.LENGTH_SHORT).show()
        }

        viewModel.showProgressBar.observe(viewLifecycleOwner){
            binding.progressBar.visibility = if (it)  View.VISIBLE
            else View.GONE
        }

        viewModel.passwordsEqualsException.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), getString(R.string.passwords_is_not_equals), Toast.LENGTH_SHORT).show()
        }

        viewModel.invalidEmailException.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), getString(R.string.invalid_email), Toast.LENGTH_SHORT).show()
        }

        viewModel.signUpException.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        viewModel.readyToBackInSignInFragment.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), getString(R.string.account_successfully_registred), Toast.LENGTH_SHORT).show()
            findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToSignInFragment())
        }
    }

}