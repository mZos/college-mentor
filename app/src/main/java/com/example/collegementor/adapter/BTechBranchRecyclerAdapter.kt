package com.example.collegementor.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.collegementor.databinding.RecyclerBtechBranchSingleItemBinding

class BTechBranchRecyclerAdapter(
    private val itemList: ArrayList<String>,
    private val onClickListener: OnClickListener
) :
    RecyclerView.Adapter<BTechBranchRecyclerAdapter.BTechViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BTechViewHolder {
        val binding = RecyclerBtechBranchSingleItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BTechViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BTechViewHolder, position: Int) {
        val branch = itemList[position]

        holder.binding.txtBranchName.text = branch
        holder.binding.rlBranchLayout.setOnClickListener {
            onClickListener.onClick(position)
        }


    }

    override fun getItemCount(): Int = itemList.size

    class BTechViewHolder(val binding: RecyclerBtechBranchSingleItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    class OnClickListener(val clickListener: (itemPosition: Int) -> Unit) {
        fun onClick(itemPosition: Int) = clickListener(itemPosition)
    }
}
