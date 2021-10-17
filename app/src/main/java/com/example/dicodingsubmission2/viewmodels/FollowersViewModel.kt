package com.example.dicodingsubmission2.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dicodingsubmission2.api.RetrofitClient
import com.example.dicodingsubmission2.data.model.DetailUserResponse
import com.example.dicodingsubmission2.data.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel : ViewModel() {
    val listFollowers = MutableLiveData<ArrayList<DetailUserResponse>>()

    fun setListFollowers(username: String) {
        RetrofitClient.apiInstance
            .getFollowers(username)
            .enqueue(object : Callback<ArrayList<DetailUserResponse>> {
                override fun onResponse(
                    call: Call<ArrayList<DetailUserResponse>>,
                    response: Response<ArrayList<DetailUserResponse>>,
                ) {
                    if (response.isSuccessful) {
                        listFollowers.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<DetailUserResponse>>, t: Throwable) {
                    Log.d("Failure", t.message!!)
                }

            })
    }

    fun getListFollowers(): LiveData<ArrayList<DetailUserResponse>> {
        return listFollowers
    }
}