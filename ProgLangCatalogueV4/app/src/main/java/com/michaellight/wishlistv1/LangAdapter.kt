package com.michaellight.wishlistv1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class LangAdapter(val plist: MutableList<Lang>, val listener: OnProductClickListener, val deleteListener: OnProductDeleteClickListener): RecyclerView.Adapter<LangAdapter.ProductViewHolder>() {

    interface OnProductDeleteClickListener {
        fun onDelete(position: Int)
    }

    interface OnProductClickListener {
        fun onProductClick(wish: Lang, position: Int)
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName = itemView.findViewById<TextView>(R.id.itemName)
        val textDate = itemView.findViewById<TextView>(R.id.itemDate)
        val textText = itemView.findViewById<TextView>(R.id.itemText)
        val photo = itemView.findViewById<ImageView>(R.id.imageView)
        val closeButton = itemView.findViewById<ImageButton>(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var holder: LangAdapter.ProductViewHolder
        holder = ProductViewHolder(inflater.inflate(R.layout.item_wish, parent, false))

        return holder
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val lang: Lang = plist[position]
        holder.textName.text = plist[position].name
        holder.textDate.text = plist[position].date
        holder.textText.text = plist[position].text.take(50) + "..."
        if (plist[position].photo == null) {
            holder.photo.setImageResource(plist[position].photoId)
        }
        else {
            holder.photo.setImageBitmap(lang.photo)
        }
        holder.itemView.setOnClickListener {
            listener.onProductClick(lang, position)
        }
        holder.closeButton.setOnClickListener() {
            deleteListener.onDelete(position)
        }
    }

    override fun getItemCount(): Int {
        return plist.count()
    }
}