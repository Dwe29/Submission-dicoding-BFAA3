package com.example.dicodingsubmission2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dicodingsubmission2.data.User
import com.example.dicodingsubmission2.databinding.ItemUserBinding

class FollowingAdapter(private val listUser: ArrayList<User>) :
    RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>() {

    inner class FollowingViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return FollowingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        with(holder) {
            with(listUser[position]) {
                binding.apply {
                    Glide.with(holder.itemView.context)
                        .load(avatar)
                        .apply(RequestOptions().override(350, 350))
                        .into(ivItemPhoto)

                    tvItemUsername.text = username
                    tvItemName.text = name
                    tvItemCompany.text = company
                }
            }
        }
    }

    override fun getItemCount(): Int = listUser.size
}