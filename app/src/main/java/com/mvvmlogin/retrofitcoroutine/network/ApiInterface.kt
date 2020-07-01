package com.chirag.retrofitcoroutine.network


import com.chirag.retrofitcoroutine.model.ArticleResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("jet2/api/v1/blogs")
    suspend fun getArticlesList(
        @Query("page") page: Int,
        @Query("limit") limit: Int = 10
    ): Response<List<ArticleResponseModel>>
}