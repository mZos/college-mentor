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

class RoadmapRecyclerAdapter(val context:Context,val itemList:ArrayList<String>):RecyclerView.Adapter<RoadmapRecyclerAdapter.RoadmapViewHolder>() {

    class RoadmapViewHolder(view: View): RecyclerView.ViewHolder(view){
        val txtRoadmaps : TextView = view.findViewById(R.id.txtRoadmaps)
        val roadmapLayout : RelativeLayout = view.findViewById(R.id.roadmapLayout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoadmapViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_roadmaps_single_row,parent,false)
        return RoadmapViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoadmapViewHolder, position: Int) {
        val text = itemList[position]
        holder.txtRoadmaps.text = text
        holder.roadmapLayout.setOnClickListener {
            Toast.makeText(context,"Roadmaps Touch",Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return  itemList.size
    }
}