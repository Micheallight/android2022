package com.michaellight.asusrecyclev1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(val plist: MutableList<Product>, val mactivity: MainActivity): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

	class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		var holder: ProductAdapter.ProductViewHolder
		holder = ProductViewHolder(inflater.inflate(R.layout.item_layout, parent, false))

		holder.itemView.setOnClickListener{
			mactivity.itemClick(holder.adapterPosition)
		}

		return holder
	}

	override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
		val textName = holder.itemView.findViewById<TextView>(R.id.itemName)
		val textPrice = holder.itemView.findViewById<TextView>(R.id.itemPrice)
//		var textRate = holder.itemView.findViewById<RatingBar>(R.id.ratingBar)
		val photo = holder.itemView.findViewById<ImageView>(R.id.imageView)

		textName.text = plist[position].name
		textPrice.text = plist[position].price.toString() + " руб."
		if (plist[position].photo == null) {
			photo.setImageResource(plist[position].photoId)
		}
		else {
			photo.setImageBitmap(plist[position].photo)
		}
//		textRate.rating = plist[position].rating.toFloat()
	}

	override fun getItemCount(): Int {
		return plist.count()
	}
}