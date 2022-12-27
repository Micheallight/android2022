package com.michaellight.productlistv1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class PostAdapter(val postModel: MutableList<PostModel>) : RecyclerView.Adapter<PostViewHolder>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.card_post, parent, false)
		return PostViewHolder(view)
	}

	override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
		return holder.bindView(postModel.get(position))
	}

	override fun getItemCount(): Int {
		return postModel.size
	}

}

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
	private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
	private val tvBody: TextView = itemView.findViewById(R.id.tvBody)


	fun bindView(postModel: PostModel) {
		tvTitle.text = postModel.title
		tvBody.text = postModel.description
	}
}