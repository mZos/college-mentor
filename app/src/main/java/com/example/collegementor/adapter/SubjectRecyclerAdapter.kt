package com.example.collegementor.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.collegementor.databinding.RecyclerSubjectSingleItemBinding

class SubjectRecyclerAdapter(
    private val subjectNameList: ArrayList<String>
) : RecyclerView.Adapter<SubjectRecyclerAdapter.SubjectViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val binding = RecyclerSubjectSingleItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return SubjectViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return subjectNameList.size
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        val subjectName = subjectNameList[position]
        holder.binding.txtSubjectName.text = subjectName
    }

    class SubjectViewHolder(val binding: RecyclerSubjectSingleItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}