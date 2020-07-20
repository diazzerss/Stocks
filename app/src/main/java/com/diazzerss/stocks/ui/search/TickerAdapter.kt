package com.diazzerss.stocks.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diazzerss.stocks.databinding.ItemTickerBinding
import com.diazzerss.stocks.model.Ticker

class TickerAdapter : RecyclerView.Adapter<TickerAdapter.TickerViewHolder>() {

    private var tickerArrayList = ArrayList<Ticker>()
    var onItemClick: ((Ticker) -> Unit)? = null

    fun addData(tickerArrayList: ArrayList<Ticker>) {
        this.tickerArrayList = tickerArrayList
        notifyDataSetChanged()
    }

    fun clearData() {
        tickerArrayList.clear()
        notifyDataSetChanged()
    }

    inner class TickerViewHolder(itemTickerBinding: ItemTickerBinding) :
        RecyclerView.ViewHolder(itemTickerBinding.root) {
        val initials: TextView = itemTickerBinding.tvTickerInitials
        var symbol: TextView = itemTickerBinding.tvTickerSymbol
        var name: TextView = itemTickerBinding.tvTickerName


        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(tickerArrayList[adapterPosition])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TickerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemTickerBinding = ItemTickerBinding.inflate(inflater, parent, false)
        return TickerViewHolder(itemTickerBinding)
    }

    override fun onBindViewHolder(holder: TickerViewHolder, position: Int) {
        holder.initials.text =
            tickerArrayList[position].symbol.trim().getOrNull(0)?.toUpperCase().toString()
        holder.symbol.text = tickerArrayList[position].symbol
        holder.name.text = tickerArrayList[position].name
    }

    override fun getItemCount(): Int {
        return tickerArrayList.size
    }

}

