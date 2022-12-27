package com.testapp.phonerecycle

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {
    @Query("SELECT * FROM products")
    fun getAll(): List<Product>

    @Query("DELETE From products")
    fun deleteAll()

    @Query("DELETE FROM products WHERE name LIKE (:productName)")
    fun deleteByName(productName: String)

    @Query("Select * FROM products WHERE name LIKE (:primaryKey)")
    fun findByPrimaryKey(primaryKey: Int): List<Product>

    @Insert
    fun addNew(newCar: Product)
}