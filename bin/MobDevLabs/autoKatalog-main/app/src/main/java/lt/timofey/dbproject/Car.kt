package lt.timofey.dbproject

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="cars")
data class Car(
    @PrimaryKey val id: Int,
    val maker: String,
    val model: String,
    val year: String,
    val engine: String,
    val country: String,
    val image: String
)