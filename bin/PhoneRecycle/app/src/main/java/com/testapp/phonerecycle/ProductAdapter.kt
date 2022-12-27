package com.testapp.phonerecycle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(val plist: MutableList<Product>, val listener: OnProductClickListener, val deleteListener: OnProductDeleteClickListener): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    interface OnProductDeleteClickListener {
        fun onDelete(position: Int)
    }

    interface OnProductClickListener {
        fun onProductClick(product: Product, position: Int)
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName = itemView.findViewById<TextView>(R.id.itemName)
        val textPrice = itemView.findViewById<TextView>(R.id.itemPrice)
        var textRate = itemView.findViewById<RatingBar>(R.id.ratingBar)
        val photo = itemView.findViewById<ImageView>(R.id.imageView)
        val closeButton = itemView.findViewById<ImageButton>(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var holder: ProductAdapter.ProductViewHolder
        holder = ProductViewHolder(inflater.inflate(R.layout.item_layout, parent, false))

        return holder
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product: Product = plist[position]
        holder.textName.text = plist[position].name
        holder.textPrice.text = plist[position].price.toString() + " руб."
        if (plist[position].photo == null) {
            holder.photo.setImageResource(plist[position].photoId)
        }
        else {
            holder.photo.setImageBitmap(product.photo)
        }
        holder.textRate.rating = plist[position].rating.toFloat()
        holder.itemView.setOnClickListener {
            listener.onProductClick(product, position)
        }
        holder.closeButton.setOnClickListener() {
            deleteListener.onDelete(position)
        }

    }

    override fun getItemCount(): Int {
        return plist.count()
    }
}