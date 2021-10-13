package com.example.dicodingsubmission2.activity

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingsubmission2.R
import com.example.dicodingsubmission2.data.User
import com.example.dicodingsubmission2.adapter.UserAdapter
import com.example.dicodingsubmission2.data.UserData
import com.example.dicodingsubmission2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var list: ArrayList<User> = arrayListOf()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvUser.setHasFixedSize(true)
        list.addAll(UserData.listData)
        showRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        return true
    }

    private fun showRecyclerView() {
        binding.rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
        val adapter = UserAdapter(list)
        binding.rvUser.adapter = adapter
    }
}