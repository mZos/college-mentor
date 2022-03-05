package com.example.collegementor.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.collegementor.databinding.RecyclerLanguageSingleRowBinding

class LanguageRecyclerAdapter(
    val context: Context,
    private val itemList: ArrayList<String>,
    val listener: OnLanguageClickListener
) : RecyclerView.Adapter<LanguageRecyclerAdapter.LanguageViewHolder>() {

    inner class LanguageViewHolder(val binding: RecyclerLanguageSingleRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val binding = RecyclerLanguageSingleRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LanguageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        val text = itemList[position]
        holder.binding.txtName.text = text
        holder.binding.layout.setOnClickListener {
            listener.onLanguageClick(position)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    interface OnLanguageClickListener {
        fun onLanguageClick(position: Int)
    }
}