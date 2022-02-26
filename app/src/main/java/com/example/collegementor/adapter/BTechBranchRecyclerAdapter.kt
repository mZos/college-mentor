package com.example.collegementor.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.collegementor.R

class BTechBranchRecyclerAdapter(private val itemList: ArrayList<String>) : RecyclerView.Adapter<BTechBranchRecyclerAdapter.BTechViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BTechViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_btech_branch_single_item, parent, false)
        return BTechViewHolder(view)
    }

    override fun onBindViewHolder(holder: BTechViewHolder, position: Int) {
    }

    override fun getItemCount(): Int = itemList.size

    class BTechViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
