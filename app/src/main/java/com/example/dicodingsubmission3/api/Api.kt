package com.example.dicodingsubmission3.api

import com.example.dicodingsubmission3.data.model.DetailUserResponse
import com.example.dicodingsubmission3.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: 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")
    fun getSearchUser(
        @Query("q") query: String,
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: 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")
    fun getUserDetail(
        @Path("username") username: String,
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: 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")
    fun getFollowers(
        @Path("username") username: String,
    ): Call<ArrayList<DetailUserResponse>>

    @GET("users/{username}/following")
    @Headers("Authorization: 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")
    fun getFollowing(
        @Path("username") username: String,
    ): Call<ArrayList<DetailUserResponse>>
}