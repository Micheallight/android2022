package com.testapp.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PersonAdapter(val plist: MutableList<Person>): RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val holder = PersonViewHolder(inflater.inflate(R.layout.item_layout, parent, false))
        return holder
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val textName = holder.itemView.findViewById<TextView>(R.id.textViewName)
        val textSecondName = holder.itemView.findViewById<TextView>(R.id.textViewSecondName)
        val textAge = holder.itemView.findViewById<TextView>(R.id.textViewAge)
        val photo = holder.itemView.findViewById<ImageView>(R.id.imageView)

        textName.text = plist[position].name
        textSecondName.text = plist[position].secondName
        textAge.text = plist[position].age.toString()
        photo.setImageResource(plist[position].photoId)
    }

    override fun getItemCount(): Int {
        return plist.count()
    }
}