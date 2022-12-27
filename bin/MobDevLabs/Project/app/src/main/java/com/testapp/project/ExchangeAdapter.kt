package com.testapp.project

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExchangeAdapter(val plist: List<Exchange>, val mactivity: MainActivity): RecyclerView.Adapter<ExchangeAdapter.ExchangeViewHolder>() {

    class ExchangeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var holder: ExchangeAdapter.ExchangeViewHolder
        holder = ExchangeViewHolder(inflater.inflate(R.layout.bank_entity, parent, false))


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

        val likeButton = holder.itemView.findViewById<Button>(R.id.likeButton)

        holder.itemView.setOnClickListener{
            mactivity.itemClick(position)
        }

        likeButton.setOnClickListener {
            mactivity.addItemToSavedList(position)
        }

        Log.d(Const.LOG_TAG,  "onBindViewHolder(): ${plist[position].toString()}")

    }

    override fun getItemCount(): Int {
        return plist.count()
    }
}