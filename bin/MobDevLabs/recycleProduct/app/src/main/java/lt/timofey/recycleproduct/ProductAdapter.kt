package lt.timofey.recycleproduct


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(val plist: MutableList<Product>, val mactivity: MainActivity): RecyclerView.Adapter<ProductAdapter.PersonViewHolder>() {

    class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var holder: ProductAdapter.PersonViewHolder //= PersonViewHolder(inflater.inflate(R.layout.item_layout, parent, false))
            holder = PersonViewHolder(inflater.inflate(R.layout.wishlist_layout, parent, false))
        holder = PersonViewHolder(inflater.inflate(R.layout.item_layout, parent, false))

        holder.itemView.setOnClickListener{
            mactivity.itemClick(holder.adapterPosition)
        }
        val btnAdd = holder.itemView.findViewById<Button>(R.id.buttonAdd)

        btnAdd.setOnClickListener {
            mactivity.addItemToWishList(holder.adapterPosition)
        }
        /*holder.itemView.setOnLongClickListener {
            mactivity.removeItem(holder.adapterPosition)
            return@setOnLongClickListener true
        }*/

        return holder
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val textName = holder.itemView.findViewById<TextView>(R.id.itemName)
        val textPrice = holder.itemView.findViewById<TextView>(R.id.itemPrice)
        var textRate = holder.itemView.findViewById<RatingBar>(R.id.ratingBar)
        val photo = holder.itemView.findViewById<ImageView>(R.id.imageView)



        textName.text = plist[position].name
        textPrice.text = plist[position].price.toString() + "$"
        if (plist[position].photo == null) {
            photo.setImageResource(plist[position].photoId)
        }
        else{
            photo.setImageBitmap(plist[position].photo)
        }
        textRate.rating = plist[position].rating.toFloat()
    }

    override fun getItemCount(): Int {
        return plist.count()
    }
}