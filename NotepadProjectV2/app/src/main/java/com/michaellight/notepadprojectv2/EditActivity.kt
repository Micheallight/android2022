package com.michaellight.notepadprojectv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.michaellight.notepadprojectv2.db.NotepadDBManager
import com.michaellight.notepadprojectv2.db.NotepadIntentConstants
import kotlinx.android.synthetic.main.edit_activity.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class EditActivity : AppCompatActivity() {
	var id = 0
	var isForEdit = false

	val notepadDBManager = NotepadDBManager(this)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.edit_activity)

		supportActionBar?.title = "Editing note"
		supportActionBar?.setDisplayHomeAsUpEnabled(true)

		getIntents()
	}

	override fun onResume() {
		super.onResume()
		notepadDBManager.openDB()
	}

	override fun onDestroy() {
		super.onDestroy()
		notepadDBManager.closeDB()
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		val inflater = menuInflater
		inflater.inflate(R.menu.menu_edit_note, menu)
		return super.onCreateOptionsMenu(menu)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.action_save_changes -> {
				val noteTitle = etTitle.text.toString()
				val noteSaveDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
				val noteText = etText.text.toString()

				if (noteTitle != "" || noteText != "") {
					if (isForEdit) {
						notepadDBManager.updateItemInDB(noteTitle, noteSaveDate, noteText, id)
					} else {
						notepadDBManager.insertToDB(noteTitle, noteSaveDate, noteText)
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
			if (i.getStringExtra(NotepadIntentConstants.INTENT_TITLE_KEY) != null) {
				etTitle.setText(i.getStringExtra(NotepadIntentConstants.INTENT_TITLE_KEY))
				etText.setText(i.getStringExtra(NotepadIntentConstants.INTENT_TEXT_KEY))

				id = i.getIntExtra(NotepadIntentConstants.INTENT_ID_KEY, 0)
				isForEdit = true
			}
		}
	}
}