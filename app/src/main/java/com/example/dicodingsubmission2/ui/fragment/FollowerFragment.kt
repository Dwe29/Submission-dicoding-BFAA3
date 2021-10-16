package com.example.dicodingsubmission2.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.dicodingsubmission2.databinding.FragmentFollowerBinding

class FollowerFragment : Fragment() {
    private lateinit var binding: FragmentFollowerBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFollowerBinding.bind(view)
    }
}