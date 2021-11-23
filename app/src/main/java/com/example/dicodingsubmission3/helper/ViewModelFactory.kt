package com.example.dicodingsubmission3.helper

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dicodingsubmission3.viewmodels.*

class ViewModelFactory constructor(private val application: Application): ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application): ViewModelFactory{
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java){
                    INSTANCE = ViewModelFactory(application)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ThemeViewModel::class.java) -> {
                ThemeViewModel(application) as T
            }

            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(application) as T
            }

            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(application) as T
            }

            modelClass.isAssignableFrom(DetailUserViewModel::class.java) -> {
                DetailUserViewModel(application) as T
            }

            modelClass.isAssignableFrom(FollowersViewModel::class.java) -> {
                FollowersViewModel(application) as T
            }

            modelClass.isAssignableFrom(FollowingViewModel::class.java) -> {
                FollowingViewModel(application) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }

    }
}