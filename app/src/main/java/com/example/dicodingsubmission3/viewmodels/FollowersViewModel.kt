package com.example.dicodingsubmission3.viewmodels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dicodingsubmission3.api.RetrofitClient
import com.example.dicodingsubmission3.data.model.DetailUserResponse
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
                        Log.d(TAG, "onResponse: ${response.body()}")
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