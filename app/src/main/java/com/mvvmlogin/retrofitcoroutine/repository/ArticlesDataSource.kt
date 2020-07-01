package com.chirag.retrofitcoroutine.repository

import androidx.paging.PageKeyedDataSource
import com.chirag.retrofitcoroutine.model.ArticleResponseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ArticlesDataSource(coroutineContext: CoroutineContext) :
    PageKeyedDataSource<Int, ArticleResponseModel>() {

    val mRepository = Repository.getInstance()
    private val job = Job()
    private val scope = CoroutineScope(coroutineContext + job)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ArticleResponseModel>
    ) {
        scope.launch {
            try {
                val response = mRepository.getArticlesList(FIRST_PAGE)
                when {
                    response.isSuccessful -> {
                        val responseItems = response.body()!!
                        responseItems.let {
                            callback.onResult(responseItems, null, FIRST_PAGE + 1)
                        }
                    }
                }

            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ArticleResponseModel>
    ) {
        scope.launch {
            try {
                val response = mRepository.getArticlesList(params.key)
                when {
                    response.isSuccessful -> {
                        val responseItems = response.body()!!
                        val key = params.key + 1
                        responseItems.let {
                            callback.onResult(responseItems, key)
                        }
                    }
                }

            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ArticleResponseModel>
    ) {
        scope.launch {
            try {
                val response = mRepository.getArticlesList(params.key)
                when {
                    response.isSuccessful -> {
                        val responseItems = response.body()!!
                        val key = if (params.key > 1) params.key - 1 else 0
                        responseItems.let {
                            callback.onResult(responseItems, key)
                        }
                    }
                }

            } catch (exception: Exception) {
                exception.printStackTrace()
            }

        }
    }

    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }

    companion object {
        const val PAGE_SIZE = 10
        const val FIRST_PAGE = 1
    }
}