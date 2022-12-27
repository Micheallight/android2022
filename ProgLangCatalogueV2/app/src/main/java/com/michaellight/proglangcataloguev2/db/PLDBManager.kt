package com.michaellight.proglangcatalogueV2.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import java.util.ArrayList

class PLDBManager(val context: Context) {
	val PLDBHelper = PLDBHelper(context)
	var db: SQLiteDatabase? = null

	fun openDB() {
		db = PLDBHelper.writableDatabase
	}

	fun insertToDB(title: String, date: String, content: String) {
		val values = ContentValues().apply {
			put(PLDBNameClass.COLUMN_NAME_TITLE, title)
			put(PLDBNameClass.COLUMN_NAME_DATE, date)
			put(PLDBNameClass.COLUMN_NAME_TEXT, content)
		}
		db?.insert(PLDBNameClass.TABLE_NAME, null, values)
	}

	fun updateItemInDB(title: String, date: String, content: String, id: Int) {
		val selection = BaseColumns._ID + "=" + id
		val values = ContentValues().apply {
			put(PLDBNameClass.COLUMN_NAME_TITLE, title)
			put(PLDBNameClass.COLUMN_NAME_DATE, date)
			put(PLDBNameClass.COLUMN_NAME_TEXT, content)
		}
		db?.update(PLDBNameClass.TABLE_NAME, values, selection, null)
	}

	fun deleteFromDB(id: String) {
		val selection = BaseColumns._ID + "=" + id
		db?.delete(PLDBNameClass.TABLE_NAME, selection, null)
	}

	fun readDBData(searchText: String) : ArrayList<ListItem> {
		val dataList = ArrayList<ListItem>()
		val selection = "${PLDBNameClass.COLUMN_NAME_TITLE} LIKE ? ORDER BY ${PLDBNameClass.COLUMN_NAME_DATE} DESC"
		val cursor = db?.query(
			PLDBNameClass.TABLE_NAME,
			null,
			selection,
			arrayOf("%$searchText%"),
			null,
			null,
			null
		)

		while (cursor?.moveToNext()!!) {
			val dataID = cursor?.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID))
			val dataTitle = cursor?.getString(cursor.getColumnIndexOrThrow(PLDBNameClass.COLUMN_NAME_TITLE))
			val dataDate = cursor?.getString(cursor.getColumnIndexOrThrow(PLDBNameClass.COLUMN_NAME_DATE))
			val dataText = cursor?.getString(cursor.getColumnIndexOrThrow(PLDBNameClass.COLUMN_NAME_TEXT))

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
		PLDBHelper.close()
	}
}