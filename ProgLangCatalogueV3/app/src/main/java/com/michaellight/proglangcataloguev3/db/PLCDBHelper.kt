package com.michaellight.proglangcataloguev3.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class PLCDBHelper(context: Context) : SQLiteOpenHelper(
	context,
	PLCDBNameClass.DATABASE_NAME,
	null,
	PLCDBNameClass.DATABASE_VERSION
) {
	override fun onCreate(db: SQLiteDatabase?) {
		db?.execSQL(PLCDBNameClass.CREATE_TABLE)
	}

	override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
		db?.execSQL(PLCDBNameClass.DELETE_TABLE)
		onCreate(db)
	}
}