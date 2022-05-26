package com.example.collegementor.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.collegementor.databinding.RecyclerGuidedPathsSingleRowBinding

class GuidedPathsRecyclerAdapter(val context: Context, private val itemList: ArrayList<String>) :
    RecyclerView.Adapter<GuidedPathsRecyclerAdapter.RoadmapViewHolder>() {

    inner class RoadmapViewHolder(val binding: RecyclerGuidedPathsSingleRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoadmapViewHolder {
        val binding = RecyclerGuidedPathsSingleRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RoadmapViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoadmapViewHolder, position: Int) {
        val text = itemList[position]
        holder.binding.txtGuidedPathTopic.text = text
        holder.binding.guidedPathLayout.setOnClickListener {
            Toast.makeText(context, "Guided Paths Touch", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}