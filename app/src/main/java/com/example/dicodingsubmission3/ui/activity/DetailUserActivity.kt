package com.example.dicodingsubmission3.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.dicodingsubmission3.R
import com.example.dicodingsubmission3.adapter.SectionPagerAdapter
import com.example.dicodingsubmission3.databinding.ActivityDetailUserBinding
import com.example.dicodingsubmission3.helper.ViewModelFactory
import com.example.dicodingsubmission3.viewmodels.DetailUserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailUserActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_AVATAR = "extra_avatar"
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
        val id = intent.getIntExtra(EXTRA_ID, 0)
        val avatarUrl = intent.getStringExtra(EXTRA_AVATAR)

        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)
        viewModel = obtainViewModel(this as AppCompatActivity)
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

        var _isChecked = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = viewModel.checkUser(id)
            withContext(Dispatchers.Main){
                if (count != null) {
                    if (count > 0) {
                        binding.toggleFavorite.isChecked = true
                        _isChecked = true
                    } else {
                        binding.toggleFavorite.isChecked = false
                        _isChecked = false
                    }
                }
            }
        }

        binding.toggleFavorite.setOnClickListener{
            _isChecked = !_isChecked
            if (_isChecked) {
                viewModel.addToFavorite(username, id, avatarUrl!!)
            } else {
                viewModel.removeFromFavorite(id)
            }
            binding.toggleFavorite.isChecked = _isChecked
        }
        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabs.setupWithViewPager(viewPager)
        }
        supportActionBar?.elevation = 0f

    }

    private fun obtainViewModel(activity: AppCompatActivity): DetailUserViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[DetailUserViewModel::class.java]
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }


}