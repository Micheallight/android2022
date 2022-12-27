package com.example.exchangerates.db

import android.os.Parcelable
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.example.exchangerates.data.Exchange
import java.io.Serializable

@Dao
interface ExchangeDao {
    @Query("SELECT * FROM exchanges")
    fun getAll(): List<Exchange>

    @Query("SELECT * FROM exchanges WHERE name LIKE (:city)")
    fun findByCity(city: String): List<Exchange>

    @Insert(onConflict = IGNORE)
    fun saveAll(exchanges: List<Exchange>)

    @Delete
    fun delete(exchange: Exchange)

    @Query("DELETE FROM exchanges")
    fun deleteAll()
}