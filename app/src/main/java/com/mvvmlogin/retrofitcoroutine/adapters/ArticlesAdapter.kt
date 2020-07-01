package com.chirag.retrofitcoroutine.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chirag.retrofitcoroutine.R
import com.chirag.retrofitcoroutine.model.ArticleResponseModel
import com.chirag.retrofitcoroutine.util.DiffUtilCallBack
import com.chirag.retrofitcoroutine.util.Utility
import kotlinx.android.synthetic.main.adapter_row.view.*


class ArticlesAdapter : PagedListAdapter<ArticleResponseModel, ArticlesAdapter.MyViewHolder>(
    DiffUtilCallBack()
) {
    lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        mContext = parent.getContext()
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let { holder.bindPost(it) }
        holder.tvArticleUrl.setOnClickListener {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse(holder.tvArticleUrl.toString()))
            mContext.startActivity(browserIntent)
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileImage = itemView.ivProfile
        val tvName = itemView.tvName
        val tvDesignation = itemView.tvDesignation

        val ivArticle = itemView.ivArticle
        val tvArticleContent = itemView.tvArticleContent
        val tvArticleName = itemView.tvArticleName
        val tvArticleUrl = itemView.tvArticleUrl
        val tvLikes = itemView.tvLikes
        val tvComments = itemView.tvComments
        val tvDuration = itemView.tvDuration

        fun bindPost(article: ArticleResponseModel) {
            Glide.with(itemView.context)
                .load(article.user.get(0).avatar)
                .circleCrop()
                .into(profileImage)
            // tvName.text = "${article.user?.get(0).name} ${article.user.get(0).lastname}"
            tvName.text = itemView.context.getString(
                R.string.full_name,
                article.user.get(0).name,
                article.user.get(0).lastname
            )
            tvDesignation.text = article.user.get(0).designation
            tvArticleContent.text = article.content
           // tvLikes.text = "${Utility.getFormattedNumber(article.likes)} likes"
            tvLikes.text = itemView.context.getString(
                R.string.likes,
                Utility.getFormattedNumber(article.likes)
            )

            tvComments.text = itemView.context.getString(
                    R.string.comments,
            Utility.getFormattedNumber(article.comments)
            )

            if (article.media.isNullOrEmpty()) {
                ivArticle.visibility = View.GONE
                tvArticleName.visibility = View.GONE
                tvArticleUrl.visibility = View.GONE
            } else {
                ivArticle.visibility = View.VISIBLE
                tvArticleName.visibility = View.VISIBLE
                tvArticleUrl.visibility = View.VISIBLE
                tvDuration.text =
                    Utility.convertUTCToLocal(article.media?.get(0)?.createdAt.toString())
                article.media.apply {
                    Glide.with(itemView.context)
                        .load(get(0)?.image)
                        .into(ivArticle)
                    tvArticleName.text = get(0)?.title
                    tvArticleUrl.text = get(0)?.url
                }
            }
        }
    }
}