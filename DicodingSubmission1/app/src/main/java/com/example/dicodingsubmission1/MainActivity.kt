package com.example.dicodingsubmission1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvUser: RecyclerView
    private var list: ArrayList<User> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvUser = findViewById(R.id.rv_user)
        rvUser.setHasFixedSize(true)
        list.addAll(UserData.listData)
        showRecyclerView()
    }

    private fun showRecyclerView() {
        rvUser.layoutManager = LinearLayoutManager(this)
        val adapter = UserAdapter(list)
        rvUser.adapter = adapter
    }
}