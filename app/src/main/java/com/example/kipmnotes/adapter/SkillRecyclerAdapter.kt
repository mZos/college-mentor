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

class SkillRecyclerAdapter(val context:Context,val itemList: ArrayList<String>): RecyclerView.Adapter<SkillRecyclerAdapter.SkillViewHolder>() {

    class SkillViewHolder(view: View):RecyclerView.ViewHolder(view){
        val txtSkillTopic:TextView = view.findViewById(R.id.txtSkillTopic)
        val skillsLayout:RelativeLayout = view.findViewById(R.id.skillsLayout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_skills_single_row,parent,false)
        return SkillViewHolder(view)
    }

    override fun onBindViewHolder(holder: SkillViewHolder, position: Int) {
        val text = itemList[position]
        holder.txtSkillTopic.text = text
        holder.skillsLayout.setOnClickListener {
            Toast.makeText(context,"Skills Touch",Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}