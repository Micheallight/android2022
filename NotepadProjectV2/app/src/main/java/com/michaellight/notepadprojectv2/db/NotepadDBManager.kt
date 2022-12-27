package com.michaellight.notepadprojectv2.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
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
			put(NotepadDBNameClass.COLUMN_NAME_TEXT, content)
		}
		db?.insert(NotepadDBNameClass.TABLE_NAME, null, values)
	}

	fun updateItemInDB(title: String, date: String, content: String, id: Int) {
		val selection = BaseColumns._ID + "=" + id
		val values = ContentValues().apply {
			put(NotepadDBNameClass.COLUMN_NAME_TITLE, title)
			put(NotepadDBNameClass.COLUMN_NAME_DATE, date)
			put(NotepadDBNameClass.COLUMN_NAME_TEXT, content)
		}
		db?.update(NotepadDBNameClass.TABLE_NAME, values, selection, null)
	}

	fun deleteFromDB(id: String) {
		val selection = BaseColumns._ID + "=" + id
		db?.delete(NotepadDBNameClass.TABLE_NAME, selection, null)
	}

	fun readDBData(searchText: String) : ArrayList<ListItem> {
		val dataList = ArrayList<ListItem>()
		val selection = "${NotepadDBNameClass.COLUMN_NAME_TITLE} LIKE ? ORDER BY ${NotepadDBNameClass.COLUMN_NAME_DATE} DESC"
		val cursor = db?.query(
			NotepadDBNameClass.TABLE_NAME,
			null,
			selection,
			arrayOf("%$searchText%"),
			null,
			null,
			null
		)

		while (cursor?.moveToNext()!!) {
			val dataID = cursor?.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID))
			val dataTitle = cursor?.getString(cursor.getColumnIndexOrThrow(NotepadDBNameClass.COLUMN_NAME_TITLE))
			val dataDate = cursor?.getString(cursor.getColumnIndexOrThrow(NotepadDBNameClass.COLUMN_NAME_DATE))
			val dataText = cursor?.getString(cursor.getColumnIndexOrThrow(NotepadDBNameClass.COLUMN_NAME_TEXT))

			var item = ListItem()

			item.id = dataID
			item.title = dataTitle
			item.date = dataDate
			item.text = dataText

			dataList.add(item)
		}

		cursor.close()
		return dataList
	}

	fun closeDB() {
		notepadDBHelper.close()
	}
}