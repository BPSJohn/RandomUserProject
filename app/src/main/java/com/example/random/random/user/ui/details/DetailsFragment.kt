package com.example.random.random.user.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.random.random.user.databinding.DetailsFragmentBinding
import com.example.random.random.user.ui.main.MainFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val detailsFragmentArgs by navArgs<DetailsFragmentArgs>()

    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.user = detailsFragmentArgs.user

        val binding = DetailsFragmentBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        binding.detailsButton.setOnClickListener { v: View ->
            v.findNavController().navigate(
                DetailsFragmentDirections.actionDetailsFragmentToLocationFragment(
                    viewModel.user
                )
            )
        }

        return binding.root
    }

}
