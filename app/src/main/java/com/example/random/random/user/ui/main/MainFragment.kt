package com.example.random.random.user.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.random.random.user.data.model.RandomUserResult
import com.example.random.random.user.databinding.MainFragmentBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var masterKey: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MainFragmentBinding.inflate(inflater)

        masterKey = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        sharedPreferences = EncryptedSharedPreferences.create(
            "StoredUser",
            masterKey,
            requireContext(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        binding.lifecycleOwner = viewLifecycleOwner

        binding.users = viewModel.randomUsersFeed.value

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

        viewModel.randomUserFeed.observe(viewLifecycleOwner) { user ->
            binding.user = user?.results?.get(0)
        }

        viewModel.randomUsersFeed.observe(viewLifecycleOwner) { users ->
            binding.users = users
        }

        binding.mainRefreshUserButton.setOnClickListener {
            viewModel.getUser()
        }

        binding.mainRefresh10UsersButton.setOnClickListener {
            viewModel.getUsers()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        val jsonObject = sharedPreferences.getString("saved-user", "")

        Timber.d("jsonObject is $jsonObject")

        val gson = Gson()

        val savedUser = gson.fromJson(jsonObject, RandomUserResult::class.java)

        viewModel.updateUser(savedUser)
    }

    override fun onPause() {
        super.onPause()

        val myEdit = sharedPreferences.edit()

        val gson = Gson()

        val jsonObject = gson.toJson(viewModel.saveUser())

        Timber.d("User is $jsonObject")

        myEdit.putString("saved-user", jsonObject)
        myEdit.apply()
    }

}
