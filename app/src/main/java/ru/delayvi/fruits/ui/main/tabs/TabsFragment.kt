package ru.delayvi.fruits.ui.main.tabs

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.delayvi.fruits.R

class TabsFragment : Fragment() {

    companion object {
        fun newInstance() = TabsFragment()
    }

    private lateinit var viewModel: TabsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tabs, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TabsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}