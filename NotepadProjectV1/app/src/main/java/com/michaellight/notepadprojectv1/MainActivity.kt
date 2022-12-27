package com.michaellight.notepadprojectv1

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.michaellight.notepadprojectv1.databinding.ActivityMainBinding
import com.michaellight.notepadprojectv1.db.NotepadDBManager
import com.michaellight.notepadprojectv1.ui.home.HomeFragment
import com.michaellight.notepadprojectv1.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_home.*

class MainActivity : AppCompatActivity() {

	private lateinit var appBarConfiguration: AppBarConfiguration
	private lateinit var binding: ActivityMainBinding

	val notepadDBManager = NotepadDBManager(this)

	var mainNoteListEl = findViewById<ConstraintLayout>(R.id.nav_host_fragment_content_main)
		.findViewById<TextView>(R.id.mainNoteList) // nav_host_fragment_content_main.mainNoteList

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		setSupportActionBar(binding.appBarMain.toolbar)

		binding.appBarMain.fab.setOnClickListener { view ->
			Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
				.setAction("Action", null).show()
		}
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

//	fun onClick(view: View) {
//		notepadDBManager.openDB()
//		notepadDBManager.insertToDB("title", "date", "text") //R.id.noteTitle.toString(), "", "")
//	}

	fun createNewNote(view: View) {
		mainNoteListEl.text = "" //	tvTest.text = ""
		notepadDBManager.openDB()
		notepadDBManager.insertToDB("title", "date", "text") //R.id.noteTitle.toString(), "", "")
		val dataList = notepadDBManager.readDBData()
		for (item in dataList) {
			mainNoteListEl.append(item)
			mainNoteListEl.append("\n")
		}
	}

	override fun onDestroy() {
		super.onDestroy()
		notepadDBManager.closeDB()
	}
}