package com.example.random.random.user.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.random.random.user.R
import com.example.random.random.user.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = MainFragmentBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        binding.mainRecyclerView.adapter = MainAdapter()

        binding.mainRecyclerView.setHasFixedSize(true)

        binding.mainUserBackground.setOnClickListener { v : View ->
            viewModel.randomUserFeed.value?.results?.get(0)?.let {
                v.findNavController()
                    .navigate(
                        MainFragmentDirections
                            .actionMainFragmentToDetailsFragment(
                                it
                            )
                    )
            }
        }

        binding.mainRefreshUserButton.setOnClickListener {
            viewModel.getUser()
        }

        binding.mainRefresh10UsersButton.setOnClickListener {
            viewModel.getUsers()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUser()
        viewModel.getUsers()
    }
}
