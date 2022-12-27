package com.testapp.project

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ExchangeSavedDao {
    @Query("SELECT * FROM currencies")
    fun getAll(): List<Exchange>

    @Query("DELETE From currencies")
    fun deleteAll()

    @Query("DELETE FROM currencies WHERE street LIKE (:street)")
    fun deleteByName(street: String)

    @Query("Select * FROM currencies WHERE id LIKE (:primaryKey)")
    fun findByPrimaryKey(primaryKey: Int): List<Exchange>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addNew(newExchange: Exchange)
}