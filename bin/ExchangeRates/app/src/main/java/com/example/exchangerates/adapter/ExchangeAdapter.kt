package com.example.exchangerates.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangerates.R
import com.example.exchangerates.data.Exchange

class ExchangeAdapter(private val exchangeList: List<Exchange>, private val listener: OnExchangeClickListener):
    RecyclerView.Adapter<ExchangeAdapter.ExchangeViewHolder>() {

    interface OnExchangeClickListener {
        fun onExchangeClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.exchange_item, parent, false)
        return ExchangeViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExchangeViewHolder, position: Int) {
        val exchange = exchangeList[position]

        holder.tvlUsdIn.text = exchange.USD_in
        holder.tvUsdOut.text = exchange.USD_out
        holder.tvCity.text = "${exchange.name_type} ${exchange.name}"

        holder.itemView.setOnClickListener { listener.onExchangeClick(position) }
    }

    override fun getItemCount(): Int {
        return exchangeList.size
    }

    class ExchangeViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvlUsdIn: TextView = view.findViewById(R.id.tvUsdIn)
        val tvUsdOut: TextView = view.findViewById(R.id.tvUsdOut)
        val tvCity: TextView = view.findViewById(R.id.tvCity)
    }
}