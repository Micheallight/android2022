package com.michaellight.proglangcataloguev2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.michaellight.proglangcatalogueV2.db.PLAdapter
//com.michaellight.proglangcatalogueV2.db.PLAdapter
import com.michaellight.proglangcatalogueV2.db.PLDBManager
import com.michaellight.proglangcataloguev2.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.fragment_gallery.*

import android.util.Log
import androidx.appcompat.widget.SearchView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
	val PLDBManager = PLDBManager(this)
	val PLAdapter = PLAdapter(ArrayList(), this)

	private lateinit var appBarConfiguration: AppBarConfiguration
	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		setSupportActionBar(binding.appBarMain.toolbar)

//		binding.appBarMain.fab.setOnClickListener { view ->
//			Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//				.setAction("Action", null).show()
//		}
		val drawerLayout: DrawerLayout = binding.drawerLayout
		val navView: NavigationView = binding.navView
		val navController = findNavController(R.id.nav_host_fragment_content_main)
		// Passing each menu ID as a set of Ids because each
		// menu should be considered as top level destinations.
		appBarConfiguration = AppBarConfiguration(
			setOf(
				R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
			), drawerLayout
		)
		setupActionBarWithNavController(navController, appBarConfiguration)
		navView.setupWithNavController(navController)
		init()
	}

	override fun onResume() {
		super.onResume()
		PLDBManager.openDB()
		fillAdapter()
	}

	override fun onDestroy() {
		super.onDestroy()
		PLDBManager.closeDB()
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		// Inflate the menu; this adds items to the action bar if it is present.
		menuInflater.inflate(R.menu.main, menu)
		return true

	}

	override fun onSupportNavigateUp(): Boolean {
		val navController = findNavController(R.id.nav_host_fragment_content_main)
		return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
	}

	fun onClickAddNew(view: View) {
		var i = Intent(this, EditActivity::class.java)
		startActivity(i)
	}

	fun init() {
		rvMain.layoutManager = LinearLayoutManager(this)
		val swapHelper = getSwapManager()
		swapHelper.attachToRecyclerView(rvMain)
		rvMain.adapter = PLAdapter
	}

	fun fillAdapter() {
		val list = PLDBManager.readDBData("")
		PLAdapter.updateAdapter(list)
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
				PLAdapter.deleteItem(viewHolder.adapterPosition, PLDBManager)
			}
		})
	}
}