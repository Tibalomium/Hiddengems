package me.megaaweso.hiddengems

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GemsRecylerAdapter(val context : Context, val gems : List<Gem>, val listener: OnClickListener) : RecyclerView.Adapter<GemsRecylerAdapter.ViewHolder>() {

    val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //TODO("Not yet implemented")
        val itemView = layoutInflater.inflate(R.layout.listitem, parent, false)

        return ViewHolder(itemView)
    }

    override fun getItemCount() = gems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //TODO("Not yet implemented")
        val gem = gems[position]

        holder.title.text = gem.title
        holder.body.text = gem.body
        holder.gemId = gem.documentId

    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.tvTitle)
        val body = itemView.findViewById<TextView>(R.id.tvBody)
        val delete = itemView.findViewById<ImageButton>(R.id.btnDelete)
        var gemId : String? = null

        init {
            delete.setOnClickListener {
                if(gemId != null) {
                    DataManager.deleteGem(gemId.toString())
                    notifyDataSetChanged()
                }
            }

            itemView.setOnClickListener {
                listener.OnClick(adapterPosition)
            }
        }
    }

    interface OnClickListener {
        fun OnClick(position: Int)
    }

}