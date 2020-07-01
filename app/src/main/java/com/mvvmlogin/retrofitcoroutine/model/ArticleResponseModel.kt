package com.chirag.retrofitcoroutine.model

import com.google.gson.annotations.SerializedName

data class ArticleResponseModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("comments")
    val comments: Int,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("media")
    val media: List<Media?>?,
    @SerializedName("user")
    val user: List<User>
)

data class User(
    @SerializedName("about")
    val about: String,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("blogId")
    val blogId: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("designation")
    val designation: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("lastname")
    val lastname: String,
    @SerializedName("name")
    val name: String
)

data class Media(
    @SerializedName("id")
    val id: String,
    @SerializedName("blogId")
    val blogId: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)



