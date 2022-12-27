package com.michaellight.wishlistv1

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WishDAO {
    @Query("SELECT * FROM wishes")
    fun getAll(): List<Wish>

    @Query("DELETE From wishes")
    fun deleteAll()

    @Query("DELETE FROM wishes WHERE date LIKE (:wishDate)")
    fun deleteByDate(wishDate: String)
//    fun deleteByName(wishName: String)

    @Query("Select * FROM wishes WHERE date LIKE (:primaryKey)")
    fun findByPrimaryKey(primaryKey: Int): List<Wish>

    @Insert
    fun addNew(newWish: Wish)
}