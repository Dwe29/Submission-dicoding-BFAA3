package com.example.dicodingsubmission2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingsubmission2.R
import com.example.dicodingsubmission2.adapter.UserAdapter
import com.example.dicodingsubmission2.data.User
import com.example.dicodingsubmission2.databinding.FragmentFollowerBinding

class FollowerFragment : Fragment() {
    private lateinit var binding: FragmentFollowerBinding
    private var list: ArrayList<User> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_follower, container, false)
        binding = FragmentFollowerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvFollower.layoutManager = LinearLayoutManager(activity)
        val adapter = UserAdapter(list)
        binding.rvFollower.adapter = adapter

    }
}