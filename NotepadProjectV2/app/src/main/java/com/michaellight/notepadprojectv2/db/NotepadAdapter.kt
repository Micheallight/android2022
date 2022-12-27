package com.michaellight.notepadprojectv2.db

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.michaellight.notepadprojectv2.EditActivity
import com.michaellight.notepadprojectv2.R

class NotepadAdapter(listMain:ArrayList<ListItem>, contextMain: Context) : RecyclerView.Adapter<NotepadAdapter.NotepadHolder>() {
	var listArray = listMain
	var context = contextMain

	class NotepadHolder(itemView: View, contextView: Context) : RecyclerView.ViewHolder(itemView) {
		val itemTitle : TextView = itemView.findViewById(R.id.itemTitle)
		val itemDate : TextView = itemView.findViewById(R.id.itemDate)

		val context = contextView

		fun setData(item: ListItem) {
			itemTitle.text = item.title
			itemDate.text = item.date

			itemView.setOnClickListener {
				val intent = Intent(context, EditActivity::class.java).apply {
					putExtra(NotepadIntentConstants.INTENT_ID_KEY, item.id)
					putExtra(NotepadIntentConstants.INTENT_TITLE_KEY, item.title)
					putExtra(NotepadIntentConstants.INTENT_DATE_KEY, item.date)
					putExtra(NotepadIntentConstants.INTENT_TEXT_KEY, item.text)
				}
				context.startActivity(intent)
			}
		}
	}

	override fun getItemCount(): Int {
		return listArray.size
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotepadHolder {
		val inflater = LayoutInflater.from(parent.context)
		return NotepadHolder(inflater.inflate(R.layout.rv_item, parent, false), context)
	}

	override fun onBindViewHolder(holder: NotepadHolder, position: Int) {
		holder.setData(listArray.get(position))
	}

	fun updateAdapter(listItems: List<ListItem>) {
		listArray.clear()
		listArray.addAll(listItems)
		notifyDataSetChanged()
	}

	fun deleteItem(position: Int, dbManager: NotepadDBManager) {
		Log.d("", position.toString())
		Log.d("", listArray[position].id.toString())
		dbManager.deleteFromDB(listArray[position].id.toString())
		listArray.removeAt(position)
		notifyItemRangeChanged(0, listArray.size)
		notifyItemRemoved(position)
	}
}