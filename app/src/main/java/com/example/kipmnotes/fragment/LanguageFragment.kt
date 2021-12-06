package com.example.kipmnotes.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kipmnotes.R
import com.example.kipmnotes.adapter.LanguageRecyclerAdapter
import com.example.kipmnotes.databinding.FragmentHomeBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class LanguageFragment : Fragment() {


    private var param1: String? = null
    private var param2: String? = null

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

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

        recyclerAdapter = LanguageRecyclerAdapter(activity as Context,languageList)
        recyclerLanguage.adapter = recyclerAdapter
        recyclerLanguage.layoutManager = layoutManager
        return view

    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            LanguageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}