package com.example.dicodingsubmission2.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dicodingsubmission2.activity.DetailUserActivity
import com.example.dicodingsubmission2.activity.DetailUserActivity.Companion.EXTRA_DATA
import com.example.dicodingsubmission2.data.User
import com.example.dicodingsubmission2.databinding.ItemUserBinding


class UserAdapter(private val listUser: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        with(holder) {
            with(listUser[position]) {
                binding.apply {
                    Glide.with(holder.itemView.context)
                        .load(avatar)
                        .apply(RequestOptions().override(350, 550))
                        .into(ivItemPhoto)

                    tvItemName.text = name
                    tvItemUsername.text = username
                    tvItemCompany.text = company
                }

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailUserActivity::class.java)
                    intent.putExtra(EXTRA_DATA, this)
                    itemView.context.startActivity(intent)
                }
            }
        }

    }

    override fun getItemCount(): Int = listUser.size
}