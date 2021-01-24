package com.diazzerss.stocks.presentation.company

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diazzerss.stocks.databinding.ItemQuoteBinding
import com.diazzerss.stocks.domain.model.Quote
import javax.inject.Inject

class QuoteAdapter @Inject constructor() : RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>() {

    private var quoteArrayList = ArrayList<Quote>()

    fun addData(quoteArrayList: ArrayList<Quote>) {
        this.quoteArrayList = quoteArrayList
        notifyDataSetChanged()
    }

    inner class QuoteViewHolder(itemQuoteBinding: ItemQuoteBinding) :
        RecyclerView.ViewHolder(itemQuoteBinding.root) {

        val prevClose: TextView = itemQuoteBinding.tvQuotePrevClose
        val open: TextView = itemQuoteBinding.tvQuoteOpen
        val dayLow: TextView = itemQuoteBinding.tvQuoteDayLow
        val dayHigh: TextView = itemQuoteBinding.tvQuoteDayHigh
        val yearLow: TextView = itemQuoteBinding.tvQuoteYearLow
        val yearHigh: TextView = itemQuoteBinding.tvQuoteYearHigh
        val volume: TextView = itemQuoteBinding.tvQuoteVolume
        val avgVolume: TextView = itemQuoteBinding.tvQuoteAvgVolume
        val marketCap: TextView = itemQuoteBinding.tvQuoteMarketCap
        val eps: TextView = itemQuoteBinding.tvQuoteEps
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemQuoteBinding = ItemQuoteBinding.inflate(inflater, parent, false)
        return QuoteViewHolder(itemQuoteBinding)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        holder.prevClose.text = quoteArrayList[position].previousClose.toString()
        holder.open.text = quoteArrayList[position].open.toString()
        holder.dayLow.text = quoteArrayList[position].dayLow.toString()
        holder.dayHigh.text = quoteArrayList[position].dayHigh.toString()
        holder.yearLow.text = quoteArrayList[position].yearLow.toString()
        holder.yearHigh.text = quoteArrayList[position].yearHigh.toString()
        holder.volume.text = quoteArrayList[position].volume.toString()
        holder.avgVolume.text = quoteArrayList[position].avgVolume.toString()
        holder.marketCap.text = quoteArrayList[position].marketCap.toString()
        holder.eps.text = quoteArrayList[position].eps.toString()
    }

    override fun getItemCount(): Int {
        return quoteArrayList.size
    }

}