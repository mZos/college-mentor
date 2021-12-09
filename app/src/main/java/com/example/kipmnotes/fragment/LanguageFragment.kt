package com.example.kipmnotes.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kipmnotes.R
import com.example.kipmnotes.adapter.LanguageRecyclerAdapter
import com.example.kipmnotes.databinding.FragmentHomeBinding
import com.example.kipmnotes.languageFragments.CPPFragment
import com.google.android.gms.dynamic.SupportFragmentWrapper

class LanguageFragment : Fragment(),LanguageRecyclerAdapter.OnLanguageClickListener  {

    lateinit var recyclerLanguage:RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var recyclerAdapter:LanguageRecyclerAdapter

    val languageList = arrayListOf<String>(
        "  Learn Python",
        "  Learn Java",
        "  Learn C++",
        "  Learn Javascript",
        "  Learn C",
        "  Learn SQL",
        "  Learn R"
        )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_language,container,false)

        recyclerLanguage = view.findViewById(R.id.recyclerLanguage)
        layoutManager = LinearLayoutManager(activity)

//        Added Divider b/w items
        recyclerLanguage.addItemDecoration(
            DividerItemDecoration(
                recyclerLanguage.context,(layoutManager as LinearLayoutManager).orientation
            )
        )

        recyclerAdapter = LanguageRecyclerAdapter(activity as Context,languageList,this)
        recyclerLanguage.adapter = recyclerAdapter
        recyclerLanguage.layoutManager = layoutManager
        return view
    }

    override fun onLanguageClick(position: Int) {
        Toast.makeText(context,"item $position",Toast.LENGTH_LONG).show()
        if (position == 3){
            
        }
    }

}