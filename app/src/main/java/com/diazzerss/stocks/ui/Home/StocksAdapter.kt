package com.diazzerss.stocks.ui.Home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diazzerss.stocks.R
import com.diazzerss.stocks.models.Stock
import com.diazzerss.stocks.utils.*

/**
 * Created by Diaz on 06.05.2020.
 */
class StocksAdapter : RecyclerView.Adapter<StocksAdapter.StocksViewHolder>() {

    private var stockArrayList = ArrayList<Stock>()
    var onItemClick: ((Stock) -> Unit)? = null

    fun addData(stockArrayList: ArrayList<Stock>) {
        this.stockArrayList = stockArrayList
        notifyDataSetChanged()
    }

    fun clearData() {
        stockArrayList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StocksViewHolder {
        return StocksViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_stock, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StocksViewHolder, position: Int) {
        holder.initials.text = stockArrayList[position].ticker.toInitials()
        holder.name.text = stockArrayList[position].companyName
        holder.ticker.text = stockArrayList[position].ticker
        holder.price.text = (stockArrayList[position].price.formatDouble())
        holder.changes.text = stockArrayList[position].changes.addSign()
        holder.changesPercentage.text = stockArrayList[position].changesPercentage.removeBrackets()

        //TODO Исправить!
        if (stockArrayList[position].changes > 0) {
            holder.changes.setTextColor(Color.parseColor("#4CAF50"))
            holder.changesPercentage.setBackgroundResource(R.drawable.rounded_corner_green)
        } else {
            holder.changes.setTextColor(Color.parseColor("#F44336"))
            holder.changesPercentage.setBackgroundResource(R.drawable.rounded_corner_red)
        }


    }

    override fun getItemCount(): Int {
        return stockArrayList.size
    }

    inner class StocksViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val initials: TextView = view.findViewById(R.id.tv_stock_initials)
        var name: TextView = view.findViewById(R.id.tv_stock_name)
        val ticker: TextView = view.findViewById(R.id.tv_stock_ticker)
        val price: TextView = view.findViewById(R.id.tv_stock_price)
        val changes: TextView = view.findViewById(R.id.tv_stock_changes)
        val changesPercentage: TextView = view.findViewById(R.id.tv_stock_changes_percentage)


        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(stockArrayList[adapterPosition])
            }
        }

    }


}