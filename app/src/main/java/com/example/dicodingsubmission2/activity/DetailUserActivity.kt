package com.example.dicodingsubmission2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.dicodingsubmission2.R
import com.example.dicodingsubmission2.SectionPagerAdapter
import com.example.dicodingsubmission2.data.User
import com.example.dicodingsubmission2.databinding.ActivityDetailUserBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding

    companion object {
        const val EXTRA_DATA = "extra_data"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getString(R.string.detail_user)

        val dataUser = intent.getParcelableExtra<User>(EXTRA_DATA) as User

        binding.apply {
            Glide.with(this@DetailUserActivity)
                .load(dataUser.avatar)
                .into(ivProfile)

            tvName.text = dataUser.name
            tvUsername.text = getString(R.string.username, dataUser.username)
            tvRepository.text = getString(R.string.repository, dataUser.repository)
            tvFollowers.text = getString(R.string.followers, dataUser.follower)
            tvFollowing.text = getString(R.string.following, dataUser.following)
            tvCompany.text = getString(R.string.company, dataUser.company)
            tvLocation.text = getString(R.string.location, dataUser.location)
        }

        callTabLayout()

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun callTabLayout() {
        val sectionPagerAdapter = SectionPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f
    }


}