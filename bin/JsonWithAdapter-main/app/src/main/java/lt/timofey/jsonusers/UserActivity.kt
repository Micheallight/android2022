package lt.timofey.jsonusers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class UserActivity : AppCompatActivity() {
    val REQUEST_CODE = 10
    var currentUser = User(1,"1111111111111111111115222222222222222222222","","",Address("","","","",Geo("","")),"","",Company("","",""))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_user)
        val name = findViewById<TextView>(R.id.name)
        val username = findViewById<TextView>(R.id.username)
        val email = findViewById<TextView>(R.id.email)
        val address = findViewById<TextView>(R.id.address)
        val phone = findViewById<TextView>(R.id.phone)
        val website = findViewById<TextView>(R.id.website)
        val company = findViewById<TextView>(R.id.company)
        val id = findViewById<TextView>(R.id.userId)
        currentUser = intent.getParcelableExtra<User>("product")!!
        id.text = currentUser.id.toString()
        name.text = currentUser.name
        username.text = currentUser.username
        email.text = currentUser.email
        address.text = "\tstreet: " + currentUser.address.street + "\n\tsuite: " + currentUser.address.suite + "\n\tcity: " + currentUser.address.city + "\n\tZipcode: " + currentUser.address.zipcode+"\n\n\tGeo:\n\t\tlat: " + currentUser.address.geo.lat + "\n\t\tlng" + currentUser.address.geo.lng
        phone.text = currentUser.phone
        website.text = currentUser.website
        company.text = "\tcompany name: " + currentUser.company.name + "\n\tbs: " + currentUser.company.bs + "\n\tcatch Phrase: " + currentUser.company.catchPhrase

    }

override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
        currentUser = data!!.getParcelableExtra<User>("product")!!
        }
}
}