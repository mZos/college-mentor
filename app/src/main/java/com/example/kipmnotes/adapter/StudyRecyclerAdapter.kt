package com.example.kipmnotes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.kipmnotes.R

class StudyRecyclerAdapter(val context: Context, val itemList: ArrayList<String>): RecyclerView.Adapter<StudyRecyclerAdapter.StudyViewHolder>() {

    class StudyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val txtStudyTopics: TextView = view.findViewById(R.id.txtStudyTopics)
        val studyLayout: RelativeLayout = view.findViewById(R.id.studyLayout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_study_single_row,parent,false)
        return StudyViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudyViewHolder, position: Int) {
        val text = itemList[position]
        holder.txtStudyTopics.text = text
        holder.studyLayout.setOnClickListener {
            Toast.makeText(context,"Study Touch", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}