package ru.delayvi.fruits.ui.main.tabs.fruitboard

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
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
        viewModel.fruits.observe(viewLifecycleOwner) {

        }
        binding.fruitBoardRecyclerView.adapter = fruitboardAdapter
        val test = listOf<Fruit>(
            Fruit(1, "Apple"),
            Fruit(2, "Mango"),
            Fruit(3, "Blackberry"),
            Fruit(4, "Pineapple"),
            Fruit(5, "Dragon"),
            Fruit(6, "Blueberry"),
            Fruit(7, "Raspberry"),
            Fruit(8, "Cherry"),
            Fruit(9, "Lichi"),
            Fruit(10, "Orange"),
            Fruit(11, "Lemon"),
            Fruit(12, "Lime")
        )
        fruitboardAdapter.submitList(test)

        fruitboardAdapter.onClickListener = {
            findNavController().navigate(
                FruitboardFragmentDirections.actionFruitboardFragmentToFruitFragment(it.id, it.fruitName)
            )
        }

    }

    private val fruitClickListener = View.OnClickListener {
        val fruit = it.tag as Fruit
        val direction = FruitboardFragmentDirections.actionFruitboardFragmentToFruitFragment(
            fruit.id,
            fruit.fruitName,
        )
        findNavController().navigate(direction)
    }


}