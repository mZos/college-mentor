package com.example.collegementor.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.collegementor.databinding.RecyclerSubjectSingleItemBinding
import com.example.collegementor.modal.BTech

class SubjectRecyclerAdapter(
    private val context: Context,
    private val subjectFileList: List<BTech>,

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
        return subjectFileList.size
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        val btech = subjectFileList[position]

        val isExpanded = btech.isExpanded

        holder.binding.llSubItems.visibility = if (isExpanded) View.VISIBLE else View.GONE
        holder.binding.txtSubjectName.text = btech.subjectName

        holder.binding.rvNested.apply {
            adapter = SubjectNestedItemAdapter(btech.fileName)
            layoutManager = LinearLayoutManager(holder.itemView.context)
        }

        holder.binding.rlBranchYearlayout.setOnClickListener {
            btech.isExpanded = !isExpanded
            notifyItemChanged(position)
        }
    }

    class SubjectViewHolder(val binding: RecyclerSubjectSingleItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}