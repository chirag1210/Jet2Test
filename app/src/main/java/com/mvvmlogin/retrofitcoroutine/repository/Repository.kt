package com.chirag.retrofitcoroutine.repository

import com.chirag.retrofitcoroutine.network.RetrofitClient

class Repository private constructor() {

    companion object {
        private var mInstance: Repository? = null
        fun getInstance(): Repository {
            if (mInstance == null) {
                synchronized(this) {
                    mInstance =
                        Repository()
                }
            }
            return mInstance!!
        }
    }

    suspend fun getArticlesList(page: Int) = RetrofitClient.api.getArticlesList(page = page)
}