package com.chirag.retrofitcoroutine.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.chirag.retrofitcoroutine.model.ArticleResponseModel
import kotlinx.coroutines.Dispatchers

class UserDataSourceFactory : DataSource.Factory<Int, ArticleResponseModel>() {
    val userLiveDataSource = MutableLiveData<ArticlesDataSource>()
    override fun create(): DataSource<Int, ArticleResponseModel> {
        val userDataSource = ArticlesDataSource(Dispatchers.IO)
        userLiveDataSource.postValue(userDataSource)
        return userDataSource
    }
}