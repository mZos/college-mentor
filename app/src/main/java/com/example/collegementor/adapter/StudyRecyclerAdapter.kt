package com.example.collegementor.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.collegementor.R

class StudyRecyclerAdapter(
    val context: Context,
    private val itemList: ArrayList<String>,
    val onClickListener: OnClickListener
) : RecyclerView.Adapter<StudyRecyclerAdapter.StudyViewHolder>() {

    class StudyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtStudyTopics: TextView = view.findViewById(R.id.txtStudyTopics)
        val studyLayout: RelativeLayout = view.findViewById(R.id.studyLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_study_single_row, parent, false)
        return StudyViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudyViewHolder, position: Int) {
        val text = itemList[position]
        holder.txtStudyTopics.text = text
        holder.studyLayout.setOnClickListener {
            onClickListener.onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class OnClickListener(val clickListener: (itemPosition: Int) -> Unit) {
        fun onClick(itemPosition: Int) = clickListener(itemPosition)
    }
}