package com.testapp.project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExchangeSavedAdapter(val plist: List<Exchange>, val savedActivity: ExchangeSavedActivity) : RecyclerView.Adapter<ExchangeSavedAdapter.ExchangeViewHolder>() {

    class ExchangeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var holder: ExchangeSavedAdapter.ExchangeViewHolder
        holder = ExchangeViewHolder(inflater.inflate(R.layout.saved_bank_entity, parent, false))

        val btnDel = holder.itemView.findViewById<Button>(R.id.deleteButton)

        holder.itemView.setOnClickListener{
            savedActivity.itemClick(holder.adapterPosition)
        }

        btnDel.setOnClickListener {
            savedActivity.deleteItemFromWishList(holder.adapterPosition)
        }

        return holder
    }

    override fun onBindViewHolder(holder: ExchangeViewHolder, position: Int) {
        val filialID = holder.itemView.findViewById<TextView>(R.id.filialId)
        val city = holder.itemView.findViewById<TextView>(R.id.city)
        var street = holder.itemView.findViewById<TextView>(R.id.street)
        val usdIn = holder.itemView.findViewById<TextView>(R.id.usdIn)
        val usdOut = holder.itemView.findViewById<TextView>(R.id.usdOut)


        filialID.text = plist[position].filial_id
        city.text = plist[position].name
        street.text = plist[position].street_type + plist[position].street + " " + plist[position].home_number
        usdIn.text = plist[position].USD_in
        usdOut.text = plist[position].USD_out
    }

    override fun getItemCount(): Int {
        return plist.count()
    }

}