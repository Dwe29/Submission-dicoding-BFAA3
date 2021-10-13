package com.example.dicodingsubmission2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dicodingsubmission2.data.User
import com.example.dicodingsubmission2.databinding.ItemUserBinding

class FollowerAdapter(private val listUser: ArrayList<User>) :
    RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {
    inner class FollowerViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        with(holder) {
            with(listUser[position]) {
                binding.apply {
                    Glide.with(holder.itemView.context)
                        .load(avatar)
                        .apply(RequestOptions().override(350, 350))
                        .into(ivItemPhoto)

                    tvItemCompany.text = company
                    tvItemName.text = name
                    tvItemUsername.text = username
                }
            }
        }
    }

    override fun getItemCount(): Int = listUser.size
}