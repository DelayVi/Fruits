package ru.delayvi.fruits.ui.main.tabs.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.delayvi.fruits.R

class EditUsernameFragment : Fragment() {

    companion object {
        fun newInstance() = EditUsernameFragment()
    }

    private lateinit var viewModel: EditUsernameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_username, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditUsernameViewModel::class.java)
        // TODO: Use the ViewModel
    }

}