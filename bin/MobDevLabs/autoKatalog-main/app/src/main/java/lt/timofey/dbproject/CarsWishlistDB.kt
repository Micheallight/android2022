package lt.timofey.dbproject

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Car::class], version = 1)
abstract class CarsWishlistDB : RoomDatabase() {
    abstract fun carWishlistDao(): CarWishlistDao
}