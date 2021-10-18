package com.example.dicodingsubmission2.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingsubmission2.R
import com.example.dicodingsubmission2.adapter.UserAdapter
import com.example.dicodingsubmission2.data.model.DetailUserResponse
import com.example.dicodingsubmission2.data.model.User
import com.example.dicodingsubmission2.databinding.FragmentFollowerBinding
import com.example.dicodingsubmission2.ui.activity.DetailUserActivity
import com.example.dicodingsubmission2.viewmodels.FollowersViewModel

class FollowerFragment : Fragment() {
    private val listUser: ArrayList<User> = arrayListOf()
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FollowersViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var username: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val args = arguments
        username = args?.getString(DetailUserActivity.EXTRA_USERNAME).toString()
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)

        showRecycler()
        showLoading(true)
        viewModel = ViewModelProvider(this,
            ViewModelProvider.NewInstanceFactory()).get(FollowersViewModel::class.java)
        viewModel.setListFollowers(username)
        viewModel.getListFollowers().observe(viewLifecycleOwner, {
            if (it != null) {
                adapter.setList(it)
                showLoading(false)
            }
        })
        return binding.root
    }

    private fun showRecycler() {
        adapter = UserAdapter()
        binding.apply {
            rvFollower.setHasFixedSize(true)
            rvFollower.layoutManager = LinearLayoutManager(context)
            rvFollower.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBarFollower.visibility = View.VISIBLE
        } else {
            binding.progressBarFollower.visibility = View.GONE
        }

    }

}