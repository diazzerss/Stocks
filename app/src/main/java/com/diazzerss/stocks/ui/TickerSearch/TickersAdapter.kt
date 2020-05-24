package com.diazzerss.stocks.ui.TickerSearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diazzerss.stocks.R
import com.diazzerss.stocks.models.Ticker

class TickersAdapter : RecyclerView.Adapter<TickersAdapter.TickerViewHolder>() {

    private var tickerArrayList = ArrayList<Ticker>()
    var onItemClick: ((Ticker) -> Unit)? = null

    fun addData(tickerArrayList: ArrayList<Ticker>) {
        this.tickerArrayList = tickerArrayList
        notifyDataSetChanged()
    }
    fun clearData(){
        tickerArrayList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TickerViewHolder {
        return TickerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_ticker, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TickerViewHolder, position: Int) {
        holder.symbol.text = tickerArrayList[position].symbol
        holder.name.text = tickerArrayList[position].name
    }

    override fun getItemCount(): Int {
        return tickerArrayList.size
    }

    inner class TickerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var symbol: TextView = view.findViewById(R.id.tv_symbol)
        var name: TextView = view.findViewById(R.id.tv_name)


        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(tickerArrayList[adapterPosition])
            }
        }

    }


}

