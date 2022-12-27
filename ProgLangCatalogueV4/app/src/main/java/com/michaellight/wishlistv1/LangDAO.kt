package com.michaellight.wishlistv1

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LangDAO {
    @Query("SELECT * FROM table_of_languages")
    fun getAll(): List<Lang>

    @Query("DELETE From table_of_languages")
    fun deleteAll()

    @Query("DELETE FROM table_of_languages WHERE name LIKE (:langName)")
    fun deleteByName(langName: String)
//    fun deleteByName(wishName: String)

    @Query("Select * FROM table_of_languages WHERE name LIKE (:primaryKey)")
    fun findByPrimaryKey(primaryKey: Int): List<Lang>

    @Insert
    fun addNew(newWish: Lang)
}