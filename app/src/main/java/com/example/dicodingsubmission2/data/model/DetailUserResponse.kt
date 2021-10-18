package com.example.dicodingsubmission2.data.model

data class DetailUserResponse(
    val login: String,
    val id: Int,
    val avatar_url: String,
    val followers_url: String,
    val following_url: String,
    val name: String,
    val followers: Int,
    val following: Int,
    val public_repos: Int,
    val company: String,
    val location: String
)