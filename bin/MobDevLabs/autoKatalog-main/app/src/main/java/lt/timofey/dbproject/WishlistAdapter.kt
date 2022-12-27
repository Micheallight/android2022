package lt.timofey.dbproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WishlistAdapter(val plist: List<Car>, val wactivity: WishlistActivity): RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder>() {

        class WishlistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            var holder: WishlistAdapter.WishlistViewHolder //= PersonViewHolder(inflater.inflate(R.layout.item_layout, parent, false))
            holder = WishlistViewHolder(inflater.inflate(R.layout.wishlistitem_layout, parent, false))

            /*holder.itemView.setOnClickListener{
                mactivity.itemClick(holder.adapterPosition)
            }*/
            val btnDel = holder.itemView.findViewById<Button>(R.id.buttonDelete)

            btnDel.setOnClickListener {
                wactivity.deleteItemFromWishList(holder.adapterPosition)
            }
            /*holder.itemView.setOnLongClickListener {
                mactivity.removeItem(holder.adapterPosition)
                return@setOnLongClickListener true
            }*/

            return holder
        }

        override fun onBindViewHolder(holder: WishlistViewHolder, position: Int) {
            val carMaker = holder.itemView.findViewById<TextView>(R.id.carMaker)
            val carModel = holder.itemView.findViewById<TextView>(R.id.carModel)




            carMaker.text = plist[position].maker
            carModel.text = plist[position].model
        }

        override fun getItemCount(): Int {
            return plist.count()
        }
}