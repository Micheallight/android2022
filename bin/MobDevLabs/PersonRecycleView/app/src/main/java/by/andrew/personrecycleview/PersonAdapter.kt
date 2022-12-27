package by.andrew.personrecycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PersonAdapter(val plist: MutableList<Person>, val mactivity: MainActivity): RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
      val inflater = LayoutInflater.from(parent.context)
      val holder = PersonViewHolder(inflater.inflate(R.layout.item_layout,parent,false))
      holder.itemView.setOnClickListener {
          mactivity.itemClick(holder.adapterPosition)
      }
      holder.itemView.setOnLongClickListener {
          mactivity.removeItem(holder.adapterPosition)
          return@setOnLongClickListener true
      }

      return holder
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val tName = holder.itemView.findViewById<TextView>(R.id.tvName)
        val tSName = holder.itemView.findViewById<TextView>(R.id.tvSecondName)
        val tAge = holder.itemView.findViewById<TextView>(R.id.tvAge)
        val photoView = holder.itemView.findViewById<ImageView>(R.id.imageView)



        tName.text = plist[position].name
        tSName.text = plist[position].secondName
        tAge.text = plist[position].age.toString()
        if (plist[position].photo == null) {
            photoView.setImageResource(plist[position].photoId)
        }
        else{
            photoView.setImageBitmap(plist[position].photo)
        }
    }

    override fun getItemCount(): Int {
        return plist.count()
    }
}


