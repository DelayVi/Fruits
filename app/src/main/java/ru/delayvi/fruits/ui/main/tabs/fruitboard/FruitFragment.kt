package ru.delayvi.fruits.ui.main.tabs.fruitboard

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.delayvi.fruits.R
import ru.delayvi.fruits.databinding.BottomSheetNutritionsBinding
import ru.delayvi.fruits.databinding.FragmentFruitBinding
import ru.delayvi.fruits.databinding.FragmentFruitboardBinding
import ru.delayvi.fruits.domain.fruits.entity.Nutritions

@AndroidEntryPoint
class FruitFragment : Fragment() {

    private val args by navArgs<FruitFragmentArgs>()

    private val viewModel by viewModels<FruitViewModel>()

    private var _binding: FragmentFruitBinding? = null
    private val binding: FragmentFruitBinding
        get() = _binding ?: throw RuntimeException("FragmentFruitBinding == null")
    lateinit var nutritions: Nutritions


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFruitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getNutritions(args.fruitName)

        binding.fruitTextView.text = args.fruitName
        binding.goBackButton.setOnClickListener { requireActivity().onBackPressed() }
        binding.showNutritionsButton.setOnClickListener {

            showNutritionsDialog()
        }

        viewModel.nutritions.onEach {
            if (it != null) {
                nutritions = it
            }
        }.launchIn(lifecycleScope)
    }


    private fun showNutritionsDialog() {

        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(R.layout.bottom_sheet_nutritions)

        val proteins = dialog.findViewById<TextView>(R.id.proteinsTextView)
        val fats = dialog.findViewById<TextView>(R.id.fatTextView)
        val carbohydrates = dialog.findViewById<TextView>(R.id.carbohydratesTextView)
        val calories = dialog.findViewById<TextView>(R.id.caloriesTextView)
        val sugar = dialog.findViewById<TextView>(R.id.sugarTextView)

        proteins?.text = nutritions.protein.toString()
        fats?.text = nutritions.fat.toString()
        carbohydrates?.text = nutritions.carbohydrates.toString()
        calories?.text = nutritions.calories.toString()
        sugar?.text = nutritions.sugar.toString()
        dialog.show()
    }


}