package com.mvvm.csquare.userdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs

import com.mvvm.csquare.R
import com.mvvm.csquare.databinding.FragmentUserDetailsBinding

class UserDetailsFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentUserDetailsBinding
    private val args: UserDetailsFragmentArgs by navArgs()
    private val viewModel by viewModels<UserDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_user_details, container, false)
        viewDataBinding = FragmentUserDetailsBinding.bind(root).apply {
            this.viewmodels = viewModel
        }
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        return viewDataBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        args.userData?.let {
            viewModel.setUserDetails(it)
        }
    }
}
