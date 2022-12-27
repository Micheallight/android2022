package com.michaellight.proglangcatalogueV2.db

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.michaellight.proglangcataloguev2.EditActivity
import com.michaellight.proglangcataloguev2.R

class PLAdapter(listMain:ArrayList<ListItem>, contextMain: Context) : RecyclerView.Adapter<PLAdapter.PLHolder>() {
	var listArray = listMain
	var context = contextMain

	class PLHolder(itemView: View, contextView: Context) : RecyclerView.ViewHolder(itemView) {
		val itemTitle : TextView = itemView.findViewById(R.id.itemTitle)
		val itemDate : TextView = itemView.findViewById(R.id.itemDate)

		val context = contextView

		fun setData(item: ListItem) {
			itemTitle.text = item.title
			itemDate.text = item.date

			itemView.setOnClickListener {
				val intent = Intent(context, EditActivity::class.java).apply {
					putExtra(PLIntentConstants.INTENT_ID_KEY, item.id)
					putExtra(PLIntentConstants.INTENT_TITLE_KEY, item.title)
					putExtra(PLIntentConstants.INTENT_DATE_KEY, item.date)
					putExtra(PLIntentConstants.INTENT_TEXT_KEY, item.text)
				}
				context.startActivity(intent)
			}
		}
	}

	override fun getItemCount(): Int {
		return listArray.size
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PLHolder {
		val inflater = LayoutInflater.from(parent.context)
		return PLHolder(inflater.inflate(R.layout.rv_item, parent, false), context)
	}

	override fun onBindViewHolder(holder: PLHolder, position: Int) {
		holder.setData(listArray.get(position))
	}

	fun updateAdapter(listItems: List<ListItem>) {
		listArray.clear()
		listArray.addAll(listItems)
		notifyDataSetChanged()
	}

	fun deleteItem(position: Int, dbManager: PLDBManager) {
		Log.d("", position.toString())
		Log.d("", listArray[position].id.toString())
		dbManager.deleteFromDB(listArray[position].id.toString())
		listArray.removeAt(position)
		notifyItemRangeChanged(0, listArray.size)
		notifyItemRemoved(position)
	}
}