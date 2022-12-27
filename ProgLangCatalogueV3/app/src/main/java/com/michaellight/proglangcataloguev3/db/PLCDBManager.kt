package com.michaellight.proglangcataloguev3.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import java.util.ArrayList

class PLCDBManager(val context: Context) {
	val plcDBHelper = PLCDBHelper(context)
	var db: SQLiteDatabase? = null

	fun openDB() {
		db = plcDBHelper.writableDatabase
	}

	fun insertToDB(title: String, date: String, content: String) {
		val values = ContentValues().apply {
			put(PLCDBNameClass.COLUMN_NAME_TITLE, title)
			put(PLCDBNameClass.COLUMN_NAME_DATE, date)
			put(PLCDBNameClass.COLUMN_NAME_TEXT, content)
		}
		db?.insert(PLCDBNameClass.TABLE_NAME, null, values)
	}

	fun updateItemInDB(title: String, date: String, content: String, id: Int) {
		val selection = BaseColumns._ID + "=" + id
		val values = ContentValues().apply {
			put(PLCDBNameClass.COLUMN_NAME_TITLE, title)
			put(PLCDBNameClass.COLUMN_NAME_DATE, date)
			put(PLCDBNameClass.COLUMN_NAME_TEXT, content)
		}
		db?.update(PLCDBNameClass.TABLE_NAME, values, selection, null)
	}

	fun deleteFromDB(id: String) {
		val selection = BaseColumns._ID + "=" + id
		db?.delete(PLCDBNameClass.TABLE_NAME, selection, null)
	}

	fun readDBData(searchText: String) : ArrayList<ListItem> {
		val dataList = ArrayList<ListItem>()
		val selection = "${PLCDBNameClass.COLUMN_NAME_TITLE} LIKE ? ORDER BY ${BaseColumns._ID}"
		val cursor = db?.query(
			PLCDBNameClass.TABLE_NAME,
			null,
			selection,
			arrayOf("%$searchText%"),
			null,
			null,
			null
		)

		while (cursor?.moveToNext()!!) {
			val dataID = cursor?.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID))
			val dataTitle = cursor?.getString(cursor.getColumnIndexOrThrow(PLCDBNameClass.COLUMN_NAME_TITLE))
			val dataDate = cursor?.getString(cursor.getColumnIndexOrThrow(PLCDBNameClass.COLUMN_NAME_DATE))
			val dataText = cursor?.getString(cursor.getColumnIndexOrThrow(PLCDBNameClass.COLUMN_NAME_TEXT))

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
		plcDBHelper.close()
	}
}