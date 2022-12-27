package lt.timofey.jsonusers



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(val plist: List<User>, val mactivity: MainActivity): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var holder: UserAdapter.UserViewHolder //= PersonViewHolder(inflater.inflate(R.layout.item_layout, parent, false))
        holder = UserViewHolder(inflater.inflate(R.layout.user_item, parent, false))

        holder.itemView.setOnClickListener{
            mactivity.itemClick(holder.adapterPosition)
        }
        /*holder.itemView.setOnLongClickListener {
            mactivity.removeItem(holder.adapterPosition)
            return@setOnLongClickListener true
        }*/

        return holder
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val textID = holder.itemView.findViewById<TextView>(R.id.userId)
        val textName = holder.itemView.findViewById<TextView>(R.id.userName)
        var textEmail = holder.itemView.findViewById<TextView>(R.id.userEmail)
        val textAddress = holder.itemView.findViewById<TextView>(R.id.userAddress)
        val textPhone = holder.itemView.findViewById<TextView>(R.id.userPhone)
        var textCompany = holder.itemView.findViewById<TextView>(R.id.userCompany)



        textName.text = plist[position].name
        textID.text = plist[position].id.toString()
        textEmail.text = plist[position].email
        textAddress.text = plist[position].address.city.toString()
        textPhone.text = plist[position].phone
        textCompany.text = plist[position].company.name.toString()
    }

    override fun getItemCount(): Int {
        return plist.count()
    }
}