package com.example.database1

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersonDao {
	@Query("SELECT * FROM persons")
	fun getAll(): List<Person>

	@Query("DELETE FROM persons")
	fun deleteAll()

	@Query("DELETE FROM persons WHERE name LIKE (:userName)")
	fun deleteByName(userName: String)

	@Insert
	fun addNew(newPerson: Person)
}