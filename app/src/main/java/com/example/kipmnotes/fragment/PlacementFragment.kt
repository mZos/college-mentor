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
import com.example.kipmnotes.adapter.PlacementRecyclerAdapter


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PlacementFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    lateinit var recyclerPlacement: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var recyclerAdapter: PlacementRecyclerAdapter

    val placementTopicList = arrayListOf<String>(
        "  Data Structure",
        "  Algorithms",
        "  Aptitude",
        "  Interview Questions",
        "  Project Ideas"
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
        val view = inflater.inflate(R.layout.fragment_placement, container, false)

        recyclerPlacement = view.findViewById(R.id.recyclerPlacement)
        layoutManager = LinearLayoutManager(activity)


//      Added Divider b/w items
        recyclerPlacement.addItemDecoration(
            DividerItemDecoration(
                recyclerPlacement.context,(layoutManager as LinearLayoutManager).orientation
            )
        )


        recyclerAdapter = PlacementRecyclerAdapter(activity as Context,placementTopicList)
        recyclerPlacement.adapter = recyclerAdapter
        recyclerPlacement.layoutManager = layoutManager

        return view
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            PlacementFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}