package com.example.collegementor.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.collegementor.databinding.RecyclerStudySingleRowBinding

class StudyRecyclerAdapter(
    private val itemList: ArrayList<String>,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<StudyRecyclerAdapter.StudyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudyViewHolder {
        val binding = RecyclerStudySingleRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StudyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudyViewHolder, position: Int) {
        val text = itemList[position]
        holder.binding.txtTitle.text = text
        holder.binding.studyLayout.setOnClickListener {
            onClickListener.onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class StudyViewHolder(val binding: RecyclerStudySingleRowBinding) : RecyclerView.ViewHolder(binding.root)

    class OnClickListener(val clickListener: (itemPosition: Int) -> Unit) {
        fun onClick(itemPosition: Int) = clickListener(itemPosition)
    }
}