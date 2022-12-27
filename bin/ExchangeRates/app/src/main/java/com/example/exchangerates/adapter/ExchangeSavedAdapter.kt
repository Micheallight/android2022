package com.example.exchangerates.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangerates.R
import com.example.exchangerates.data.Exchange
import kotlinx.android.synthetic.main.exchange_saved_item.view.*

class ExchangeSavedAdapter (
    private val exchangeList: List<Exchange>,
    private val clickListener: OnExchangeClickListener,
    private val deleteClickListener: OnExchangeDeleteClickListener):
    RecyclerView.Adapter<ExchangeSavedAdapter.ExchangeSavedViewHolder>() {

    interface OnExchangeClickListener {
        fun onExchangeClick(position: Int)
    }

    interface OnExchangeDeleteClickListener {
        fun onExchangeDeleteClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeSavedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.exchange_saved_item, parent, false)
        return ExchangeSavedViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExchangeSavedViewHolder, position: Int) {
        val exchange = exchangeList[position]

        holder.tvlUsdIn.text = exchange.USD_in
        holder.tvUsdOut.text = exchange.USD_out
        holder.tvCity.text = "${exchange.name_type} ${exchange.name}"

        holder.itemView.setOnClickListener { clickListener.onExchangeClick(position) }
        holder.itemView.btnDelete.setOnClickListener { deleteClickListener.onExchangeDeleteClick(position) }
    }

    override fun getItemCount(): Int {
        return exchangeList.size
    }

    class ExchangeSavedViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvlUsdIn: TextView = view.findViewById(R.id.tvUsdIn)
        val tvUsdOut: TextView = view.findViewById(R.id.tvUsdOut)
        val tvCity: TextView = view.findViewById(R.id.tvCity)
    }
}