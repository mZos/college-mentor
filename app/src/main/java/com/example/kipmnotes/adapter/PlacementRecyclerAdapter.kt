package com.example.kipmnotes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.kipmnotes.R

class PlacementRecyclerAdapter(val context : Context, val itemList:ArrayList<String>):RecyclerView.Adapter<PlacementRecyclerAdapter.PlacementViewHolder>() {


    class PlacementViewHolder(view: View): RecyclerView.ViewHolder(view){
        val txtPlacementTopic : TextView = view.findViewById(R.id.txtPlacementTopic)
        val placementLayout : RelativeLayout = view.findViewById(R.id.placementLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacementViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_placement_single_row,parent,false)
        return PlacementViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlacementViewHolder, position: Int) {
        val text = itemList[position]
        holder.txtPlacementTopic.text = text
        holder.placementLayout.setOnClickListener{
            Toast.makeText(context,"Placement Touch", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}