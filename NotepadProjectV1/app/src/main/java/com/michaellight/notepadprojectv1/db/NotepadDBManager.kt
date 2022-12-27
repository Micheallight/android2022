package com.michaellight.notepadprojectv1.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import java.util.ArrayList

class NotepadDBManager(val context: Context) {
	val notepadDBHelper = NotepadDBHelper(context)
	var db: SQLiteDatabase? = null

	fun openDB() {
		db = notepadDBHelper.writableDatabase
	}

	fun insertToDB(title: String, date: String, content: String) {
		val values = ContentValues().apply {
			put(NotepadDBNameClass.COLUMN_NAME_TITLE, title)
			put(NotepadDBNameClass.COLUMN_NAME_DATE, date)
			put(NotepadDBNameClass.COLUMN_NAME_CONTENT, content)
		}
		db?.insert(NotepadDBNameClass.TABLE_NAME, null, values)
	}

	fun readDBData() : ArrayList<String> {
		val dataList = ArrayList<String>()
		val cursor = db?.query(
			NotepadDBNameClass.TABLE_NAME,
			null,
			null,
			null,
			null,
			null,
			null
		)

		while (cursor?.moveToNext()!!) {
			val dataText = cursor?.getString(cursor.getColumnIndexOrThrow(NotepadDBNameClass.COLUMN_NAME_TITLE))
			dataList.add(dataText.toString())
		}

		cursor.close()
		return dataList
	}

	fun closeDB() {
		notepadDBHelper.close()
	}
}