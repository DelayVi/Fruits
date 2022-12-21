package ru.delayvi.fruits.ui.main.tabs.fruitboard

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.delayvi.fruits.R
import ru.delayvi.fruits.databinding.FragmentFruitboardBinding
import ru.delayvi.fruits.databinding.FragmentSignInBinding
import ru.delayvi.fruits.domain.fruits.entity.Fruit
import ru.delayvi.fruits.ui.main.auth.SignInViewModel
import ru.delayvi.fruits.ui.main.tabs.fruitboard.recycler_view.FruitboardAdapter

@AndroidEntryPoint
class FruitboardFragment : Fragment() {

    private val viewModel by viewModels<FruitboardViewModel>()

    private var _binding: FragmentFruitboardBinding? = null
    private val binding: FragmentFruitboardBinding
        get() = _binding ?: throw RuntimeException("FragmentFruitboardBinding == null")

    private var fruitboardAdapter = FruitboardAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFruitboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            binding.fruitBoardRecyclerView.adapter = fruitboardAdapter
            viewModel.fruits.onEach {
                if (it == emptyList<Fruit>()) {
                    noFruitsTextView.visibility = View.VISIBLE
                    fruitBoardRecyclerView.visibility = View.GONE
                } else {
                    noFruitsTextView.visibility = View.GONE
                    fruitBoardRecyclerView.visibility = View.VISIBLE
                }
                fruitboardAdapter.submitList(it)
            }.launchIn(lifecycleScope)
        }
        fruitboardAdapter.onClickListener = {
            findNavController().navigate(
                FruitboardFragmentDirections.actionFruitboardFragmentToFruitFragment(it.id, it.fruitName)
            )
        }

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, onBackPressedCallback)

    }


}