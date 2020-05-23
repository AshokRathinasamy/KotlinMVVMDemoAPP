package com.mvvm.csquare.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mvvm.csquare.CSquareApplication
import com.mvvm.csquare.data.source.model.DataUserList
import com.mvvm.csquare.databinding.FragmentDashBoardBinding
import com.mvvm.csquare.utils.EventObserver

class DashBoardFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentDashBoardBinding
    private val viewModel by viewModels<DashBoardFragViewModel> {
        val application = (requireContext().applicationContext as CSquareApplication)
        DashViewModelFactory(application, application.taskRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewDataBinding = FragmentDashBoardBinding.inflate(inflater, container, false).apply {
            viewmodels = viewModel
        }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        setupListAdapter()
        setupNavigation()
    }

    private fun setupListAdapter() {
        val taskListAdapter = UsersAdapter(viewModel)
        viewDataBinding.tasksList.adapter = taskListAdapter
    }


    private fun setupNavigation() {
        viewModel.intentEvent.observe(viewLifecycleOwner, EventObserver {
            navigateToAddNewTask(it)
        })
    }

    private fun navigateToAddNewTask(userList: DataUserList?) {
        val action = DashBoardFragmentDirections.actionDashBoardFragmentToUserDetailsFragment(userList)
        findNavController().navigate(action)
    }
}
