package com.chirag.retrofitcoroutine.util

import androidx.recyclerview.widget.DiffUtil
import com.chirag.retrofitcoroutine.model.ArticleResponseModel

class DiffUtilCallBack : DiffUtil.ItemCallback<ArticleResponseModel>() {
    override fun areItemsTheSame(oldItem: ArticleResponseModel, newItem: ArticleResponseModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ArticleResponseModel, newItem: ArticleResponseModel): Boolean {
        return oldItem.comments == newItem.comments
                && oldItem.content == newItem.content
    }

}