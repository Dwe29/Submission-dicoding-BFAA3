package com.example.dicodingsubmission2.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.dicodingsubmission2.R
import com.example.dicodingsubmission2.adapter.SectionPagerAdapter
import com.example.dicodingsubmission2.databinding.ActivityDetailUserBinding
import com.example.dicodingsubmission2.viewmodels.DetailUserViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USERNAME = "extra_username"

    }

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getString(R.string.detail_user)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)
        viewModel = ViewModelProvider(this,
            ViewModelProvider.NewInstanceFactory()).get(DetailUserViewModel::class.java)
        viewModel.setUserDetail(username!!)
        viewModel.user.observe(this, {
            if (it != null) {
                binding.apply {
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatar_url)
                        .centerCrop()
                        .into(ivProfile)
                    tvName.text = it.name
                    tvUsername.text = getString(R.string.username, it.login)
                    tvRepository.text = getString(R.string.repository, it.public_repos.toString())
                    tvFollowers.text = getString(R.string.followers, it.followers.toString())
                    tvFollowing.text = getString(R.string.following, it.following.toString())
                    tvCompany.text = getString(R.string.companyDetail, it.company)
                    tvLocation.text = getString(R.string.location, it.location)
                }
            }
        })
        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabs.setupWithViewPager(viewPager)
        }
        supportActionBar?.elevation = 0f

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }


}