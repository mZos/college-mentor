package com.example.collegementor.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.collegementor.databinding.RecyclerSkillsSingleRowBinding

class SkillRecyclerAdapter(
    val context: Context,
    private val itemList:
    ArrayList<String>
) : RecyclerView.Adapter<SkillRecyclerAdapter.SkillViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillViewHolder {
        val binding = RecyclerSkillsSingleRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SkillViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SkillViewHolder, position: Int) {
        val text = itemList[position]
        holder.binding.txtSkillTopic.text = text
        holder.binding.skillsLayout.setOnClickListener {
            Toast.makeText(context, "Skills Touch", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class SkillViewHolder(val binding: RecyclerSkillsSingleRowBinding) :
        RecyclerView.ViewHolder(binding.root)
}
