package ru.delayvi.fruits.ui.main.tabs.fruitboard

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import ru.delayvi.fruits.R

@AndroidEntryPoint
class FruitFragment : Fragment() {

    val args by navArgs<FruitFragmentArgs>()

    companion object {
        fun newInstance() = FruitFragment()
    }

    private lateinit var viewModel: FruitViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fruit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(requireContext(), args.fruitName, Toast.LENGTH_LONG).show()
    }

}