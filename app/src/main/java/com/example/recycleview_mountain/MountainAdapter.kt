package com.example.recycleview_mountain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class MountainAdapter constructor(private val getActivity: MainActivity,
                                  private val mountainList: List<Mountain>) :
    RecyclerView.Adapter<MountainAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_model,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.mountainTitle.text = mountainList[position].Title
        holder.mountainImage.setImageResource(mountainList[position].drawableId)
        holder.cardView.setOnClickListener {
            Toast.makeText(getActivity, mountainList[position].Title, Toast.LENGTH_SHORT).show()
        }
    }


    override fun getItemCount(): Int {
        return mountainList.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val mountainTitle : TextView = itemView.findViewById(R.id.itemText)
        val mountainImage : ImageView = itemView.findViewById(R.id.itemImage)
        val cardView : CardView = itemView.findViewById(R.id.cardView)

    }


}


