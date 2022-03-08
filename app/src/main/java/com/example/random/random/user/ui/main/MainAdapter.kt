package com.example.random.random.user.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.random.random.user.data.model.User
import com.example.random.random.user.databinding.ListItemUserBinding

class MainAdapter : ListAdapter<User, MainAdapter.ViewHolder>(DiffCallback) {
    class ViewHolder(private var binding: ListItemUserBinding) :
            RecyclerView.ViewHolder(binding.root) {
                fun bind(user: User) {
                    binding.user = user
                    binding.executePendingBindings()

                    binding.root.setOnClickListener { v: View ->
                        v.findNavController()
                            .navigate(
                                MainFragmentDirections
                                    .actionMainFragmentToDetailsFragment(
                                        user
                                    )
                            )
                    }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemUserBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userFeed = getItem(position)
        holder.bind(userFeed)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id?.value == newItem.id?.value
        }
    }
}
