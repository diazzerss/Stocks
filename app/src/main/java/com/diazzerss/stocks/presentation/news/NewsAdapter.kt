package com.diazzerss.stocks.presentation.news

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.diazzerss.stocks.R
import com.diazzerss.stocks.databinding.ItemNewsBinding
import com.diazzerss.stocks.domain.model.Article
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class NewsAdapter @Inject constructor(): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var newsArrayList = ArrayList<Article>()
    var onItemClick: ((Article) -> Unit)? = null

    fun addData(articlesArrayList: ArrayList<Article>) {
        this.newsArrayList = articlesArrayList
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(itemNewsBinding: ItemNewsBinding) :
        RecyclerView.ViewHolder(itemNewsBinding.root) {
        val image: ImageView = itemNewsBinding.newsImage
        val title: TextView = itemNewsBinding.tvNewsTitle
        val source: TextView = itemNewsBinding.tvNewsSource
        val publishedTime: TextView = itemNewsBinding.tvNewsPublishedTime


        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(newsArrayList[adapterPosition])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemNewsBinding = ItemNewsBinding.inflate(inflater, parent, false)
        return NewsViewHolder(itemNewsBinding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        Glide
            .with(holder.itemView)
            .load(newsArrayList[position].urlToImage)
            .placeholder(R.drawable.ic_placeholder_24dp)
            .error(R.drawable.ic_placeholder_24dp)
            .into(holder.image)

        holder.title.text = newsArrayList[position].title
        holder.source.text = newsArrayList[position].source.name
        val sdf = SimpleDateFormat(
            "yyyy-MM-d'T'HH:mm:ss'Z'",
            Locale.ROOT
        ).parse(newsArrayList[position].publishedAt)
        holder.publishedTime.text = SimpleDateFormat("dd MMMM, HH:mm", Locale.forLanguageTag("RU")).format(sdf)
    }

    override fun getItemCount(): Int {
        return newsArrayList.size
    }


}