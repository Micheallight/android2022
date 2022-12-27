package com.michaellight.proglangcataloguev3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.michaellight.proglangcataloguev3.db.PLCDBManager
import com.michaellight.proglangcataloguev3.db.PLCIntentConstants
import kotlinx.android.synthetic.main.edit_activity.*

class EditActivity : AppCompatActivity() {
	var id = 0
	var isForEdit = false

	val plcDBManager = PLCDBManager(this)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.edit_activity)

		supportActionBar?.title = "Editing language info"
		supportActionBar?.setDisplayHomeAsUpEnabled(true)

		getIntents()
	}

	override fun onResume() {
		super.onResume()
		plcDBManager.openDB()
	}

	override fun onDestroy() {
		super.onDestroy()
		plcDBManager.closeDB()
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		val inflater = menuInflater
		inflater.inflate(R.menu.menu_edit_note, menu)
		return super.onCreateOptionsMenu(menu)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.action_save_changes -> {
				val langTitle = etTitle.text.toString()
				val langDate = etDate.text.toString()
				val langText = etText.text.toString()

				if (langTitle != "") {
					if (isForEdit) {
						plcDBManager.updateItemInDB(langTitle, langDate, langText, id)
					} else {
						plcDBManager.insertToDB(langTitle, langDate, langText)
					}
					finish()
				}
				return true
			}
		}
		return super.onOptionsItemSelected(item)
	}

	fun getIntents() {
		val i = intent
		if (i != null) {
			if (i.getStringExtra(PLCIntentConstants.INTENT_TITLE_KEY) != null) {
				etTitle.setText(i.getStringExtra(PLCIntentConstants.INTENT_TITLE_KEY))
				etDate.setText(i.getStringExtra(PLCIntentConstants.INTENT_DATE_KEY))
				etText.setText(i.getStringExtra(PLCIntentConstants.INTENT_TEXT_KEY))

				id = i.getIntExtra(PLCIntentConstants.INTENT_ID_KEY, 0)
				isForEdit = true
			}
		}
	}
}