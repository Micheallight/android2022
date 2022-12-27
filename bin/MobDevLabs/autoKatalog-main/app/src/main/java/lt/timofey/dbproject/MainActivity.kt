package lt.timofey.dbproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val apiService = ApiService.create()
    var cars = listOf<Car>()
    lateinit var carsRecyclerViewList: RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //tv = findViewById<TextView>(R.id.tw1)
        getCars()

    }


    fun addItemToWishList(position: Int){
        val db = Room.databaseBuilder(
            applicationContext,
            CarsWishlistDB::class.java, "car_db"
        ).allowMainThreadQueries()//Делаем в отдельном потоке, в данном случае в главном потоке
            .build()

        val carWishlistDao = db.carWishlistDao()

        if (findById(cars[position].id)) {
            carWishlistDao.addNew(cars[position])
        }

        btnWishlist.setOnClickListener(){
            val intent = Intent(this, WishlistActivity::class.java)
            startActivity(intent)
        }
    }

    fun findById(num: Int): Boolean{
        val db = Room.databaseBuilder(
            applicationContext,
            CarsWishlistDB::class.java, "car_db"
        ).allowMainThreadQueries()//Делаем в отдельном потоке, в данном случае в главном потоке
            .build()

        val carWishlistDao = db.carWishlistDao()

        val expectedCarsFromDB = carWishlistDao.findByPrimaryKey(num)

        return expectedCarsFromDB.isEmpty()
    }

    fun getCars(){
        val call = apiService.getCars()

        call.enqueue(object: Callback<List<Car>> {

            override fun onFailure(call: Call<List<Car>>, t: Throwable) {
                Log.d("!!!",t.toString())
            }

            override fun onResponse(call: Call<List<Car>>, response: Response<List<Car>>) {
                cars = response.body()!!
                //tv.text = cars.toString()
                carsRecyclerViewList = findViewById(R.id.recyclerView)
                carsRecyclerViewList.layoutManager = LinearLayoutManager(this@MainActivity)
                carsRecyclerViewList.adapter = CarAdapter(cars,this@MainActivity)
            }
        })
    }
}