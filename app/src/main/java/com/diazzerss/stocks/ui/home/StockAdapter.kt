package com.diazzerss.stocks.ui.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diazzerss.stocks.R
import com.diazzerss.stocks.databinding.ItemHeaderBinding
import com.diazzerss.stocks.databinding.ItemStockBinding
import com.diazzerss.stocks.model.Stock
import com.diazzerss.stocks.utils.addSign
import com.diazzerss.stocks.utils.formatDouble

class StockAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var stockArrayList = ArrayList<Stock>()
    private var header = "Категория"
    var onItemClick: ((Stock) -> Unit)? = null

    companion object {
        private const val TYPE_HEADER: Int = 0
        private const val TYPE_LIST: Int = 1
    }

    fun addData(stockArrayList: ArrayList<Stock>) {
        this.stockArrayList = stockArrayList
        notifyDataSetChanged()
    }

    fun clearData() {
        stockArrayList.clear()
        notifyDataSetChanged()
    }

    fun setHeader(header: String) {
        this.header = header
    }

    inner class HeaderViewHolder(itemHeaderBinding: ItemHeaderBinding) :
        RecyclerView.ViewHolder(itemHeaderBinding.root) {
        val title: TextView = itemHeaderBinding.tvHeaderTitle
    }

    inner class StocksViewHolder(itemStockBinding: ItemStockBinding) :
        RecyclerView.ViewHolder(itemStockBinding.root) {
        val initials: TextView = itemStockBinding.tvStockInitials
        val name: TextView = itemStockBinding.tvStockName
        val ticker: TextView = itemStockBinding.tvStockTicker
        val price: TextView = itemStockBinding.tvStockPrice
        val changes: TextView = itemStockBinding.tvStockChanges
        val changesPercentage: TextView = itemStockBinding.tvStockChangesPercentage


        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(stockArrayList[adapterPosition - 1])
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TYPE_HEADER
        else TYPE_LIST
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val itemHeaderBinding = ItemHeaderBinding.inflate(inflater, parent, false)
        val itemStockBinding = ItemStockBinding.inflate(inflater, parent, false)

        return when (viewType) {
            TYPE_HEADER -> HeaderViewHolder(itemHeaderBinding)
            TYPE_LIST -> StocksViewHolder(itemStockBinding)
            else -> throw IllegalArgumentException("View type is not correct")
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is HeaderViewHolder) {
            holder.title.text = header
        }
        if (holder is StocksViewHolder) {
            val stock = stockArrayList[position - 1]
            holder.initials.text = stock.ticker.trim().getOrNull(0)?.toUpperCase().toString()
            holder.name.text = stock.companyName
            holder.ticker.text = stock.ticker
            holder.price.text = stock.price.formatDouble()
            holder.changes.text = stock.changes.addSign()
            holder.changesPercentage.text =
                stock.changesPercentage.removePrefix("(").removeSuffix(")")

            if (stock.changes > 0) {
                holder.changes.setTextColor(Color.parseColor("#4CAF50"))
                holder.changesPercentage.setBackgroundResource(R.drawable.rounded_corner_green)
            } else {
                holder.changes.setTextColor(Color.parseColor("#F44336"))
                holder.changesPercentage.setBackgroundResource(R.drawable.rounded_corner_red)
            }
        }

    }

    override fun getItemCount(): Int {
        return stockArrayList.size + 1
    }

}


