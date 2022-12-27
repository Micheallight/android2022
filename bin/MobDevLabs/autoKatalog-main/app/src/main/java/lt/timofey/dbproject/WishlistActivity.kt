package lt.timofey.dbproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class WishlistActivity : AppCompatActivity() {

    var carsDB = listOf<Car>()
    lateinit var carsWishlistRecyclerViewList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wishlist)

        val db = Room.databaseBuilder(
            applicationContext,
            CarsWishlistDB::class.java, "car_db"
        ).allowMainThreadQueries()//Делаем в отдельном потоке, в данном случае в главном потоке
            .build()

        val carWishlistDao = db.carWishlistDao()

        carsDB = carWishlistDao.getAll()
        carsWishlistRecyclerViewList = findViewById(R.id.recyclerViewWishlist)
        carsWishlistRecyclerViewList.layoutManager = LinearLayoutManager(this@WishlistActivity)
        carsWishlistRecyclerViewList.adapter = WishlistAdapter(carsDB,this@WishlistActivity)
    }

    fun deleteItemFromWishList(position: Int){
        val db = Room.databaseBuilder(
            applicationContext,
            CarsWishlistDB::class.java, "car_db"
        ).allowMainThreadQueries()//Делаем в отдельном потоке, в данном случае в главном потоке
            .build()

        val carWishlistDao = db.carWishlistDao()

        val expectedCars = carWishlistDao.findByPrimaryKey(carsDB[position].id)
        carWishlistDao.deleteByName(expectedCars[0].id)

        carsDB = carWishlistDao.getAll()
        carsWishlistRecyclerViewList = findViewById(R.id.recyclerViewWishlist)
        carsWishlistRecyclerViewList.layoutManager = LinearLayoutManager(this@WishlistActivity)
        carsWishlistRecyclerViewList.adapter = WishlistAdapter(carsDB,this@WishlistActivity)
    }
}