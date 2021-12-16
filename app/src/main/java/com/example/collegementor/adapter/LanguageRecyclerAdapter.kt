package com.example.collegementor.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.collegementor.R

class LanguageRecyclerAdapter(

    val context: Context,
    private val itemList: ArrayList<String>,
    val listener : OnLanguageClickListener

    ): RecyclerView.Adapter<LanguageRecyclerAdapter.LanguageViewHolder>() {

    inner class LanguageViewHolder(view: View):RecyclerView.ViewHolder(view),View.OnClickListener{
        val txtName :TextView = view.findViewById(R.id.txtName)
        val layout :RelativeLayout = view.findViewById(R.id.layout)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position:Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onLanguageClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_language_single_row,parent,false)
        return LanguageViewHolder(view)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        val text = itemList[position]
        holder.txtName.text = text
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    interface OnLanguageClickListener{
        fun onLanguageClick(position: Int)
    }

}