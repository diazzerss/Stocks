package com.diazzerss.stocks.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diazzerss.stocks.R
import com.diazzerss.stocks.databinding.ItemArticleBinding
import com.diazzerss.stocks.databinding.ItemQuoteBinding
import com.diazzerss.stocks.model.Article
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ArticlesViewHolder>() {

    private var articlesArrayList = ArrayList<Article>()
    var onItemClick: ((Article) -> Unit)? = null

    fun addData(articlesArrayList: ArrayList<Article>) {
        this.articlesArrayList = articlesArrayList
        notifyDataSetChanged()
    }

    inner class ArticlesViewHolder(itemArticleBinding: ItemArticleBinding) : RecyclerView.ViewHolder(itemArticleBinding.root) {
        val image: ImageView = itemArticleBinding.articleImage
        val title: TextView = itemArticleBinding.tvArticleTitle
        val source: TextView = itemArticleBinding.tvArticleSource
        val publishedTime: TextView = itemArticleBinding.tvArticlePublishedTime


        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(articlesArrayList[adapterPosition])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemArticleBinding = ItemArticleBinding.inflate(inflater, parent, false)
        return ArticlesViewHolder(itemArticleBinding)
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        if (articlesArrayList[position].urlToImage.isNullOrEmpty()) {
            Picasso.get().load(R.drawable.ic_placeholder_black_24dp).into(holder.image)
        } else {
            Picasso.get().load(articlesArrayList[position].urlToImage).into(holder.image)
        }
        holder.title.text = articlesArrayList[position].title
        holder.source.text = articlesArrayList[position].source.name
        //TODO Исправить дату
        val sdf = SimpleDateFormat("yyyy-MM-d'T'HH:mm:ss'Z'", Locale.ROOT).parse(articlesArrayList[position].publishedAt)
        holder.publishedTime.text = SimpleDateFormat("d-MMM, HH:mm", Locale.ROOT).format(sdf)
    }

    override fun getItemCount(): Int {
        return articlesArrayList.size
    }


}