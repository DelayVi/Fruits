package ru.delayvi.fruits.ui.main.tabs.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.delayvi.fruits.R
import ru.delayvi.fruits.databinding.FragmentEditUsernameBinding
import ru.delayvi.fruits.databinding.FragmentProfileBinding

@AndroidEntryPoint
class EditUsernameFragment : Fragment() {

    private val viewModel by viewModels<ProfileViewModel>()

    private var _binding: FragmentEditUsernameBinding? = null
    private val binding: FragmentEditUsernameBinding
        get() = _binding ?: throw RuntimeException("FragmentEditUsernameBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditUsernameBinding.inflate(inflater, container, false)
        return binding.root
    }


}