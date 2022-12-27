package lt.timofey.jsonusers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val apiService = ApiService.create()
    //lateinit var tv: TextView
    lateinit var usersList: RecyclerView
    var users = listOf<User>()
    val REQUEST_CODE = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //tv = findViewById<TextView>(R.id.tw)
        val btnUsers = findViewById<Button>(R.id.btnUsers)
        btnUsers.setOnClickListener(){
            //getComments()
            getUsers()
            usersList = findViewById(R.id.recyclerView)
            usersList.layoutManager = LinearLayoutManager(this)
            usersList.adapter = UserAdapter(users,this)
        }
    }
    fun itemClick(position: Int){
        val intent = Intent(this, UserActivity::class.java)
        intent.putExtra("product", users[position])
        startActivityForResult(intent, REQUEST_CODE)
    }
    fun getUsers(){
        val call = apiService.getUsers()

        call.enqueue(object: Callback<List<User>>{

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("!!!",t.toString())
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                users = response.body()!!
                //tv.text = users.toString()
            }
        })
    }
}