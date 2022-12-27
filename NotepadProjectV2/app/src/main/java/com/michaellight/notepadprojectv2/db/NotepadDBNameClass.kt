package com.michaellight.notepadprojectv2.db

import android.provider.BaseColumns

object NotepadDBNameClass : BaseColumns {
	const val TABLE_NAME = "table_of_notes"
	const val COLUMN_NAME_TITLE = "title"
	const val COLUMN_NAME_DATE = "date"
	const val COLUMN_NAME_TEXT = "content"

	const val DATABASE_VERSION = 1
	const val DATABASE_NAME = "TableOfNotes.db"

	const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
			"${BaseColumns._ID} INTEGER PRIMARY KEY, " +
			"$COLUMN_NAME_TITLE TEXT, " +
			"$COLUMN_NAME_DATE TEXT, " +
			"$COLUMN_NAME_TEXT TEXT" +
			")"

	const val DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}