package ru.delayvi.fruits.ui.main.tabs.fruitboard

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.delayvi.fruits.R

class FruitboardFragment : Fragment() {

    companion object {
        fun newInstance() = FruitboardFragment()
    }

    private lateinit var viewModel: FruitboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fruitboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FruitboardViewModel::class.java)
        // TODO: Use the ViewModel
    }

}