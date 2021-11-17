package com.example.dicodingsubmission3.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dicodingsubmission3.api.RetrofitClient
import com.example.dicodingsubmission3.data.model.DetailUserResponse
import com.example.dicodingsubmission3.data.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    val listUsers = MutableLiveData<ArrayList<DetailUserResponse>>()
    fun setSearchUsers(query: String) {
        RetrofitClient.apiInstance
            .getSearchUser(query)
            .enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>,
                ) {
                    if (response.isSuccessful) {
                        listUsers.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d("Failure", t.message!!)
                }
            })
    }

    fun getSearchUsers(): LiveData<ArrayList<DetailUserResponse>> {
        return listUsers
    }
}