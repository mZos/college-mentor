package com.example.collegementor.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.collegementor.databinding.RecyclerNestedSubjectItemBinding

class SubjectNestedItemAdapter(private val fileNameList: ArrayList<String>) :
    RecyclerView.Adapter<SubjectNestedItemAdapter.SubjectNestedItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectNestedItemViewHolder {
        val binding = RecyclerNestedSubjectItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SubjectNestedItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubjectNestedItemViewHolder, position: Int) {
        val fileName = fileNameList[position]
        holder.binding.txtFileName.text = fileName
    }

    override fun getItemCount(): Int = fileNameList.size

    class SubjectNestedItemViewHolder(val binding: RecyclerNestedSubjectItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}