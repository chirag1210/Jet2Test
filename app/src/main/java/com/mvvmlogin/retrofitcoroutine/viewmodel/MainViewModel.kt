package com.chirag.retrofitcoroutine.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.chirag.retrofitcoroutine.model.ArticleResponseModel
import com.chirag.retrofitcoroutine.repository.ArticlesDataSource
import com.chirag.retrofitcoroutine.repository.UserDataSourceFactory

class MainViewModel : ViewModel() {
    var userPagedList: LiveData<PagedList<ArticleResponseModel>>
    private var liveDataSource: MutableLiveData<ArticlesDataSource>

    init {
        val itemDataSourceFactory = UserDataSourceFactory()
        liveDataSource = itemDataSourceFactory.userLiveDataSource
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(ArticlesDataSource.PAGE_SIZE)
            .build()
        userPagedList = LivePagedListBuilder(itemDataSourceFactory, config)
            .build()
    }
}