package lt.timofey.dbproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CarAdapter(val plist: List<Car>, val mactivity: MainActivity): RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var holder: CarAdapter.CarViewHolder
        holder = CarViewHolder(inflater.inflate(R.layout.item_layout, parent, false))

        holder.itemView.setOnClickListener{
            //mactivity.itemClick(holder.adapterPosition)
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

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val carMaker = holder.itemView.findViewById<TextView>(R.id.carMaker)
        val carModel = holder.itemView.findViewById<TextView>(R.id.carModel)



        carMaker.text = plist[position].maker
        carModel.text = plist[position].model
    }

    override fun getItemCount(): Int {
        return plist.count()
    }
}