package com.michaellight.proglangcataloguev3.db

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//import com.michaellight.notepadprojectv2.R
import com.michaellight.proglangcataloguev3.EditActivity
import com.michaellight.proglangcataloguev3.R

class PLCAdapter(listMain:ArrayList<ListItem>, contextMain: Context) : RecyclerView.Adapter<PLCAdapter.PLCHolder>() {
	var listArray = listMain
	var context = contextMain

	class PLCHolder(itemView: View, contextView: Context) : RecyclerView.ViewHolder(itemView) {
		val itemTitle : TextView = itemView.findViewById(R.id.itemTitle)
		val itemDate : TextView = itemView.findViewById(R.id.itemDate)
		val itemDescription : TextView = itemView.findViewById(R.id.itemDescription)

		val context = contextView

		fun setData(item: ListItem) {
			itemTitle.text = item.title
			itemDate.text = item.date
			itemDescription.text = item.text.take(50) + "..."

			itemView.setOnClickListener {
				val intent = Intent(context, EditActivity::class.java).apply {
					putExtra(PLCIntentConstants.INTENT_ID_KEY, item.id)
					putExtra(PLCIntentConstants.INTENT_TITLE_KEY, item.title)
					putExtra(PLCIntentConstants.INTENT_DATE_KEY, item.date)
					putExtra(PLCIntentConstants.INTENT_TEXT_KEY, item.text)
				}
				context.startActivity(intent)
			}
		}
	}

	override fun getItemCount(): Int {
		return listArray.size
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PLCHolder {
		val inflater = LayoutInflater.from(parent.context)
		return PLCHolder(inflater.inflate(R.layout.rv_item, parent, false), context)
	}

	override fun onBindViewHolder(holder: PLCHolder, position: Int) {
		holder.setData(listArray.get(position))
	}

	fun updateAdapter(listItems: List<ListItem>) {
		listArray.clear()
		listArray.addAll(listItems)
		notifyDataSetChanged()
	}

	fun deleteItem(position: Int, dbManager: PLCDBManager) {
		Log.d("", position.toString())
		Log.d("", listArray[position].id.toString())
		dbManager.deleteFromDB(listArray[position].id.toString())
		listArray.removeAt(position)
		notifyItemRangeChanged(0, listArray.size)
		notifyItemRemoved(position)
	}
}