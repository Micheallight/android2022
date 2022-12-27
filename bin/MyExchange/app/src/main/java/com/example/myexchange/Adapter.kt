package com.example.myexchange

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class Adapter(val plist: List<Rate>, val mainActivity: MainActivity) :
    RecyclerView.Adapter<Adapter.ExchangeViewHolder>() {

    class ExchangeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var holder: ExchangeViewHolder =
            ExchangeViewHolder(inflater.inflate(R.layout.tmp, parent, false))

        holder.itemView.setOnClickListener {
            mainActivity.itemClick(holder.adapterPosition)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ExchangeViewHolder, position: Int) {
        var date = holder.itemView.findViewById<TextView>(R.id.date)
        var sellIso = holder.itemView.findViewById<TextView>(R.id.sellIso)
        var sellRate = holder.itemView.findViewById<TextView>(R.id.sellRate)
        var buyRate = holder.itemView.findViewById<TextView>(R.id.buyRate)
        var buyIso = holder.itemView.findViewById<TextView>(R.id.buyIso)
        var quantity = holder.itemView.findViewById<TextView>(R.id.quantity_value)
        var sellCode = holder.itemView.findViewById<TextView>(R.id.sellCode_value)
        var buyCode = holder.itemView.findViewById<TextView>(R.id.buyCode_value)

        date.text = plist[position].date
        sellIso.text = plist[position].sellIso
        sellRate.text = plist[position].sellRate.toString()
        buyRate.text = plist[position].buyRate.toString()
        buyIso.text = plist[position].buyIso
        quantity.text = plist[position].quantity.toString()
        sellCode.text = plist[position].sellCode.toString()
        buyCode.text = plist[position].buyCode.toString()
    }

    override fun getItemCount(): Int {
        return plist.count()
    }
}
