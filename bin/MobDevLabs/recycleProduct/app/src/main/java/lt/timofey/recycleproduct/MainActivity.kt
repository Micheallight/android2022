package lt.timofey.recycleproduct

import  android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity(){

    val products = mutableListOf<Product>()

    lateinit var productsList: RecyclerView

    val wishlistProduct = mutableListOf<Product>()

    lateinit var productsWishList: RecyclerView

    var pos= 0;
    val REQUEST_CODE = 10
    var pr = Product
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        products.add(Product("Fender\nStratocaster",2000, R.drawable.p1,4.5,"With its alluring curves, sparkling sounds and groundbreaking tremolo bridge, Fender’s new 1954 Stratocaster® was a phenomenon, unlike anything the world had ever seen. By 1957, the Stratocaster had crystallized into a mid-century masterpiece.",null))
        products.add(Product("Fender\nStratocaster Blue",1800, R.drawable.p2,4.4,"With its alluring curves, sparkling sounds and groundbreaking tremolo bridge, Fender’s new 1954 Stratocaster® was a phenomenon, unlike anything the world had ever seen. By 1957, the Stratocaster had crystallized into a mid-century masterpiece.",null))
        products.add(Product("Fender\nTelecaster",2000, R.drawable.p3,4.3,"The Fender Telecaster, colloquially known as the Tele, is an electric guitar produced by Fender. Together with its sister model the Esquire, it is the world's first mass-produced, commercially successful[note 1] solid-body electric guitar. Its simple yet effective design and revolutionary sound broke ground and set trends in electric guitar manufacturing and popular music.",null))
        products.add(Product("Fender\nJaguar",2200, R.drawable.p4,5.0,"The Fender Jaguar is an electric guitar by Fender Musical Instruments characterized by an offset-waist body, a relatively unusual switching system with two separate circuits for lead and rhythm, and a short-scale 24\" neck.",null))
        products.add(Product("Fender\nJazzMaster Player",2000, R.drawable.p5,3.8,"The Fender Jazzmaster is an electric guitar designed as a more expensive sibling of the Fender Stratocaster. First introduced at the 1958 NAMM Show, it was initially marketed to jazz guitarists, but found favor among surf rock guitarists in the early 1960s. ",null))
        products.add(Product("Fender\nJazzMaster",1400, R.drawable.p6,4.2,"The Fender Jazzmaster is an electric guitar designed as a more expensive sibling of the Fender Stratocaster. First introduced at the 1958 NAMM Show, it was initially marketed to jazz guitarists, but found favor among surf rock guitarists in the early 1960s. ",null))
        products.add(Product("Fender\nJazzMaster SunBurst",2022, R.drawable.p7,4.1,"The Fender Jazzmaster is an electric guitar designed as a more expensive sibling of the Fender Stratocaster. First introduced at the 1958 NAMM Show, it was initially marketed to jazz guitarists, but found favor among surf rock guitarists in the early 1960s. ",null))
        products.add(Product("Fender\nDuoSonic",1222, R.drawable.p8,4.7,"The Fender Duo-Sonic is an electric guitar launched by Fender Musical Instruments Corporation as a student model guitar, an inexpensive model aimed at amateur musicians. It was referred to as a \"3/4 size\" Fender guitar.",null))


        productsList = findViewById(R.id.recyclerView)
        productsList.layoutManager = LinearLayoutManager(this)
        productsList.adapter = ProductAdapter(products, this)


        val btnToWish = findViewById<Button>(R.id.btnWishlist)
        val btnAdd = findViewById<Button>(R.id.btnAdd)

        btnAdd.setOnClickListener {
            val intent = Intent(this, ItemActivity::class.java)
            //startActivity(intent)
            startActivityForResult(intent, REQUEST_CODE)
        }
        btnToWish.setOnClickListener {
            changeView()
            //setContentView(R.layout.wishlist_main)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            /*val newPerson = Person(data!!.getStringExtra("name")!!,
                data!!.getStringExtra("secondname")!!,
            data!!.getIntExtra("age",22)!!,
            R.drawable.p6,
                null
            )*/
            val newPerson = data!!.getParcelableExtra<Product>("product")!!
            products.add(newPerson)
            productsList.adapter?.notifyItemInserted(products.size-1)
        }

    }



    fun itemClick(position: Int){
        pos = position;
        Toast.makeText(this,"Hello, ${products[position].name}",Toast.LENGTH_LONG).show()

        val textView = TextView(this)

        with(textView) {
            textView.text = products[position].name
            textView.textSize = 24.0F
            textView.setTextColor(Color.parseColor("#FF00F0"));
            textView.setTypeface(null, Typeface.BOLD)
            textView.gravity = Gravity.CENTER
        }
        AlertDialog.Builder(this)
            //.setTitle(products[position].name)
            .setCustomTitle(textView)
            .setMessage("price: ${products[position].price}\nRate: ${products[position].rating}\nDescription: ${products[position].description}")
            .setPositiveButton(android.R.string.ok, null)
            .show()
    }
    fun addItemToWishList(position: Int){
        wishlistProduct.add(products[position])
    }

    fun delItemFromWishList(position: Int){
        wishlistProduct.removeAt(position)
        productsWishList.adapter?.notifyItemRemoved(position)
    }
    fun changeView(){
       setContentView(R.layout.wishlist_main)
       val btnBack = findViewById<Button>(R.id.btnBack)
        productsWishList = findViewById(R.id.recyclerView2)
        productsWishList.layoutManager = LinearLayoutManager(this)
        productsWishList.adapter = WhishListAdapter(wishlistProduct, this)
       btnBack.setOnClickListener {
           changeActivityView()
       }

    }

    fun changeActivityView(){
        setContentView(R.layout.activity_main)
        productsList = findViewById(R.id.recyclerView)
        productsList.layoutManager = LinearLayoutManager(this)
        productsList.adapter = ProductAdapter(products, this)


        val btnToWish = findViewById<Button>(R.id.btnWishlist)

        btnToWish.setOnClickListener {
            changeView()
            //setContentView(R.layout.wishlist_main)
        }
        val btnAdd = findViewById<Button>(R.id.btnAdd)

        btnAdd.setOnClickListener {
            val intent = Intent(this, ItemActivity::class.java)
            //startActivity(intent)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }


}