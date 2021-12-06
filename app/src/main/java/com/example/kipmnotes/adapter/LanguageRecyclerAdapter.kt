package com.example.kipmnotes.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.kipmnotes.R

class LanguageRecyclerAdapter(val context: Context,val itemList: ArrayList<String>):RecyclerView.Adapter<LanguageRecyclerAdapter.LanguageViewHolder>() {


    class LanguageViewHolder(view: View):RecyclerView.ViewHolder(view){
        val txtName :TextView = view.findViewById(R.id.txtName)
        val layout :RelativeLayout = view.findViewById(R.id.layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_language_single_row,parent,false)
        return LanguageViewHolder(view)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        val text = itemList[position]
        holder.txtName.text = text
        holder.layout.setOnClickListener{
            Toast.makeText(context,"Hello Friends",Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}