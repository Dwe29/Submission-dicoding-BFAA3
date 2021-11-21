package com.example.dicodingsubmission3.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dicodingsubmission3.data.local.FavoriteUser
import com.example.dicodingsubmission3.data.local.FavoriteUserDao
import com.example.dicodingsubmission3.data.local.UserDatabase
import com.example.dicodingsubmission3.data.model.DetailUserResponse

class FavoriteViewModel(application: Application): AndroidViewModel(application) {
    val user = MutableLiveData<DetailUserResponse>()

    private var userDao: FavoriteUserDao?
    private var userDb: UserDatabase? = UserDatabase.getDatabase(application)

    init {
        userDao = userDb?.favoriteUserDao()
    }

    fun getFavoriteUser(): LiveData<List<FavoriteUser>>? {
        return userDao?.getFavoriteUser()
    }
}