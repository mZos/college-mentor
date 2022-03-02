package com.example.collegementor.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.collegementor.databinding.RecyclerBranchYearSingleItemBinding

class BranchYearRecyclerAdapter(
    private val onClickListener: OnClickListener
) :
    RecyclerView.Adapter<BranchYearRecyclerAdapter.BranchYearViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BranchYearViewHolder {
        val binding = RecyclerBranchYearSingleItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BranchYearViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BranchYearViewHolder, position: Int) {
        holder.binding.txtBranchYear.text = when (position) {
            0 -> {
                " 1st Year"
            }
            1 -> {
                " 2nd Year"
            }
            2 -> {
                " 3rd Year"
            }
            3 -> {
                " 4th Year"
            }
            else -> {
                "${position + 1} Year"
            }
        }
        holder.binding.rlBranchYearlayout.setOnClickListener {
            onClickListener.onClick(position)
        }
    }

    override fun getItemCount(): Int = 4

    class BranchYearViewHolder(val binding: RecyclerBranchYearSingleItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    class OnClickListener(val clickListener: (itemPosition: Int) -> Unit) {
        fun onClick(itemPosition: Int) = clickListener(itemPosition)
    }
}
