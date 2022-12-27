package com.michaellight.proglangcatalogueV2.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class PLDBHelper(context: Context) : SQLiteOpenHelper(
	context,
	PLDBNameClass.DATABASE_NAME,
	null,
	PLDBNameClass.DATABASE_VERSION
) {
	override fun onCreate(db: SQLiteDatabase?) {
		db?.execSQL(PLDBNameClass.CREATE_TABLE)
	}

	override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
		db?.execSQL(PLDBNameClass.DELETE_TABLE)
		onCreate(db)
	}
}