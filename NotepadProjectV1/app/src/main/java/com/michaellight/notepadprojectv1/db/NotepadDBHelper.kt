package com.michaellight.notepadprojectv1.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NotepadDBHelper(context: Context) : SQLiteOpenHelper(
	context,
	NotepadDBNameClass.DATABASE_NAME,
	null,
	NotepadDBNameClass.DATABASE_VERSION
) {
	override fun onCreate(db: SQLiteDatabase?) {
		db?.execSQL(NotepadDBNameClass.CREATE_TABLE)
	}

	override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
		db?.execSQL(NotepadDBNameClass.DELETE_TABLE)
		onCreate(db)
	}
}