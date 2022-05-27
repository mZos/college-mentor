package com.example.collegementor.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.collegementor.databinding.RecyclerNestedSubjectItemBinding

class SubjectNestedItemAdapter(
    private val notesList: HashMap<String, String>,
    val listener: OnSubjectFileClickListener
) :
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
        val fileNameList: ArrayList<String> = arrayListOf()
        notesList.keys.forEach {
            fileNameList.add(it)
        }
        fileNameList.sort()
        val fileName = fileNameList[position]
        holder.binding.txtFileName.text = fileName
        holder.binding.llNestedItemLayout.setOnClickListener {
            listener.onFileClickListener(notesList[fileName]!!)
        }
    }

    override fun getItemCount(): Int = notesList.size

    class SubjectNestedItemViewHolder(val binding: RecyclerNestedSubjectItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnSubjectFileClickListener {
        fun onFileClickListener(downloadLink: String)
    }
}