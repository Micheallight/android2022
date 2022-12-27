package com.michaellight.proglangcataloguev3.db

import android.provider.BaseColumns

object PLCDBNameClass : BaseColumns {
	const val TABLE_NAME = "table_of_languages"
	const val COLUMN_NAME_TITLE = "title"
	const val COLUMN_NAME_DATE = "date"
	const val COLUMN_NAME_TEXT = "content"

	const val DATABASE_VERSION = 1
	const val DATABASE_NAME = "TableOfLanguages.db"

	const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
			"${BaseColumns._ID} INTEGER PRIMARY KEY, " +
			"$COLUMN_NAME_TITLE TEXT, " +
			"$COLUMN_NAME_DATE TEXT, " +
			"$COLUMN_NAME_TEXT TEXT" +
			")"

	const val DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}