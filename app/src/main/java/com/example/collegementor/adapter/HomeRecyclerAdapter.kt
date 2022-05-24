package com.example.collegementor.adapter

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.collegementor.R
import com.example.collegementor.databinding.RecyclerHomeItemBinding
import com.example.collegementor.modal.HomeModel
import io.grpc.internal.SharedResourceHolder

class HomeRecyclerAdapter(val context: Context, val items: List<HomeModel>) : RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = RecyclerHomeItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = items[position]
        holder.binding.ivIcon.setImageDrawable(item.iconId)
        holder.binding.txtFragmentName.text = item.name
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class HomeViewHolder(val binding: RecyclerHomeItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}