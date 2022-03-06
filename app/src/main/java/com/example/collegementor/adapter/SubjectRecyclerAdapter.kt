package com.example.collegementor.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.collegementor.databinding.RecyclerSubjectSingleItemBinding
import com.example.collegementor.modal.Subject

class SubjectRecyclerAdapter(
    private val context: Context,
    private val subjectFileList: ArrayList<Subject>
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
        val subjectFile = subjectFileList[position]

        val isExpanded = subjectFile.isExpanded

        holder.binding.llSubItems.visibility = if (isExpanded) View.VISIBLE else View.GONE
        holder.binding.txtSubjectName.text = subjectFile.subjectName
        holder.binding.txtFileName.text = subjectFile.fileName

        holder.binding.rlBranchYearlayout.setOnClickListener {
            subjectFile.isExpanded = !isExpanded
            notifyItemChanged(position)
        }

        holder.binding.llSubItems.setOnClickListener {
            Toast.makeText(context, "Hellooooooo", Toast.LENGTH_SHORT).show()
        }
    }

    class SubjectViewHolder(val binding: RecyclerSubjectSingleItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}