package com.example.exchangerates

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExchangeAdapter(private val exchangeList: List<Exchange>, private val listener: OnExchangeClickListener):
    RecyclerView.Adapter<ExchangeAdapter.ExchangeViewHolder>() {

    interface OnExchangeClickListener {
        fun onExchangeClick(exchange: Exchange, position: Int)
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

        holder.itemView.setOnClickListener { listener.onExchangeClick(exchange, position) }

        Log.d("exchange", exchange.toString())
    }

    override fun getItemCount(): Int {
        return exchangeList.size
    }

    class ExchangeViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvlUsdIn = view.findViewById<View>(R.id.tvUsdIn) as TextView
        val tvUsdOut = view.findViewById<View>(R.id.tvUsdOut) as TextView
        val tvCity = view.findViewById<View>(R.id.tvCity) as TextView
    }
}