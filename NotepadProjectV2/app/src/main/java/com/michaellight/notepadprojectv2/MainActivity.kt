package com.michaellight.notepadprojectv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.michaellight.notepadprojectv2.db.NotepadAdapter
import com.michaellight.notepadprojectv2.db.NotepadDBManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
	val notepadDBManager = NotepadDBManager(this)
	val notepadAdapter = NotepadAdapter(ArrayList(), this)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		init()
	}

	override fun onResume() {
		super.onResume()
		notepadDBManager.openDB()
		fillAdapter()
	}

	override fun onDestroy() {
		super.onDestroy()
		notepadDBManager.closeDB()
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.menu_search_note, menu)

		val search = menu?.findItem(R.id.searchView)
		val searchView = search?.actionView as SearchView
		searchView.queryHint = "Search"

		searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
			override fun onQueryTextSubmit(query: String?): Boolean {
				TODO("Not yet implemented")
			}

			override fun onQueryTextChange(newText: String?): Boolean {
				val list = notepadDBManager.readDBData(newText!!)
				notepadAdapter.updateAdapter(list)
				return true
			}
		})
		return super.onCreateOptionsMenu(menu)
	}

	fun onClickAddNew(view: View) {
		var i = Intent(this, EditActivity::class.java)
		startActivity(i)
	}

	fun init() {
		rvMain.layoutManager = LinearLayoutManager(this)
		val swapHelper = getSwapManager()
		swapHelper.attachToRecyclerView(rvMain)
		rvMain.adapter = notepadAdapter
	}

	fun fillAdapter() {
		val list = notepadDBManager.readDBData("")
		notepadAdapter.updateAdapter(list)
		if (list.size > 0) {
			tvNoElements.visibility = View.GONE
		} else {
			tvNoElements.visibility = View.VISIBLE
		}
	}

	private fun getSwapManager(): ItemTouchHelper {
		return ItemTouchHelper(object: ItemTouchHelper
		.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
			override fun onMove(
				recyclerView: RecyclerView,
				viewHolder: RecyclerView.ViewHolder,
				target: RecyclerView.ViewHolder
			): Boolean {
				return false
			}

			override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
				notepadAdapter.deleteItem(viewHolder.adapterPosition, notepadDBManager)
			}
		})
	}
}