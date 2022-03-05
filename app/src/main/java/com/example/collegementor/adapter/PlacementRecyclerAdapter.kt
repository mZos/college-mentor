package com.example.collegementor.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.collegementor.databinding.RecyclerPlacementSingleRowBinding

class PlacementRecyclerAdapter(val context: Context, private val itemList: ArrayList<String>) :
    RecyclerView.Adapter<PlacementRecyclerAdapter.PlacementViewHolder>() {

    class PlacementViewHolder(val binding: RecyclerPlacementSingleRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacementViewHolder {
        val binding = RecyclerPlacementSingleRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PlacementViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlacementViewHolder, position: Int) {
        val text = itemList[position]
        holder.binding.txtPlacementTopic.text = text
        holder.binding.placementLayout.setOnClickListener {
            Toast.makeText(context, "Placement Touch", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}