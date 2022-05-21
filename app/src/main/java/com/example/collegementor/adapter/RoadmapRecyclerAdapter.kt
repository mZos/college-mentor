package com.example.collegementor.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.collegementor.databinding.RecyclerRoadmapsSingleRowBinding

class RoadmapRecyclerAdapter(val context: Context, private val itemList: ArrayList<String>) :
    RecyclerView.Adapter<RoadmapRecyclerAdapter.RoadmapViewHolder>() {

    inner class RoadmapViewHolder(val binding: RecyclerRoadmapsSingleRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoadmapViewHolder {
        val binding = RecyclerRoadmapsSingleRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RoadmapViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoadmapViewHolder, position: Int) {
        val text = itemList[position]
        holder.binding.txtRoadmaps.text = text
        holder.binding.roadmapLayout.setOnClickListener {
            Toast.makeText(context, "Roadmaps Touch", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}