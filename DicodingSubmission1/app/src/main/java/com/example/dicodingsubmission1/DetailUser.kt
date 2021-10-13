package com.example.dicodingsubmission1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailUser : AppCompatActivity() {
    private lateinit var ivAvatar: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvUsername: TextView
    private lateinit var tvCompany: TextView
    private lateinit var tvRepository: TextView
    private lateinit var tvFollowers: TextView
    private lateinit var tvFollowing: TextView
    private lateinit var tvLocation: TextView

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Detail User"

        ivAvatar = findViewById(R.id.iv_profile)
        tvName = findViewById(R.id.tv_name)
        tvUsername = findViewById(R.id.tv_username)
        tvCompany = findViewById(R.id.tv_company)
        tvFollowers = findViewById(R.id.tv_followers)
        tvFollowing = findViewById(R.id.tv_following)
        tvRepository = findViewById(R.id.tv_repository)
        tvLocation = findViewById(R.id.tv_location)

        val dataUser = intent.getParcelableExtra<User>(EXTRA_DATA) as User

        Glide.with(this@DetailUser)
            .load(dataUser.avatar)
            .into(ivAvatar)

        tvName.text = dataUser.name
        tvUsername.text = "Username: ${dataUser.username}"
        tvRepository.text = "Repository: ${dataUser.repository}"
        tvFollowers.text = "Followers: ${dataUser.follower}"
        tvFollowing.text = "Following: ${dataUser.following}"
        tvCompany.text = "Company: ${dataUser.company}"
        tvLocation.text = "Location: ${dataUser.location}"


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}