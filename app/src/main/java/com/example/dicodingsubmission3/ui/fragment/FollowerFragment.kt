package com.example.dicodingsubmission3.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingsubmission3.adapter.UserAdapter
import com.example.dicodingsubmission3.data.model.User
import com.example.dicodingsubmission3.databinding.FragmentFollowBinding
import com.example.dicodingsubmission3.helper.ViewModelFactory
import com.example.dicodingsubmission3.ui.activity.DetailUserActivity
import com.example.dicodingsubmission3.viewmodels.FollowersViewModel

class FollowerFragment : Fragment() {
    private val listUser: ArrayList<User> = arrayListOf()
    private var _binding: FragmentFollowBinding? = null
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
        _binding = FragmentFollowBinding.inflate(inflater, container, false)

        showRecycler(listUser)
        showLoading(true)
        viewModel = obtainViewModel(context as AppCompatActivity)
        viewModel.setListFollowers(username)
        viewModel.getListFollowers().observe(viewLifecycleOwner, {
            if (it != null) {
                adapter.setList(it)
                showLoading(false)
            }
        })
        return binding.root
    }

    private fun obtainViewModel(activity: AppCompatActivity): FollowersViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[FollowersViewModel::class.java]
    }

    private fun showRecycler(list: ArrayList<User>) {
        adapter = UserAdapter(list)
        binding.apply {
            rvFollower.setHasFixedSize(true)
            rvFollower.layoutManager = LinearLayoutManager(requireContext())
            rvFollower.adapter = adapter
        }
//        if (list.isNotEmpty())
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