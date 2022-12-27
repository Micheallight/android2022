package com.michaellight.wishlistv1

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class WishAdapter(val plist: MutableList<Wish>, val listener: OnProductClickListener, val deleteListener: OnProductDeleteClickListener): RecyclerView.Adapter<WishAdapter.ProductViewHolder>() {

    interface OnProductDeleteClickListener {
        fun onDelete(position: Int)
    }

    interface OnProductClickListener {
        fun onProductClick(wish: Wish, position: Int)
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName = itemView.findViewById<TextView>(R.id.itemName)
        val textPrice = itemView.findViewById<TextView>(R.id.itemPrice)
        val textDate = itemView.findViewById<TextView>(R.id.itemDate)
        val photo = itemView.findViewById<ImageView>(R.id.imageView)
        val closeButton = itemView.findViewById<ImageButton>(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var holder: WishAdapter.ProductViewHolder
        holder = ProductViewHolder(inflater.inflate(R.layout.item_wish, parent, false))

        return holder
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val wish: Wish = plist[position]
        holder.textDate.text = plist[position].date
        holder.textName.text = plist[position].name
        holder.textPrice.text = "USD " + plist[position].price
        if (plist[position].photo == null) {
            holder.photo.setImageResource(plist[position].photoId)
        }
        else {
            holder.photo.setImageBitmap(wish.photo)
        }
        holder.itemView.setOnClickListener {
            listener.onProductClick(wish, position)
        }
        holder.closeButton.setOnClickListener() {
            deleteListener.onDelete(position)
        }
    }

    override fun getItemCount(): Int {
        return plist.count()
    }
}