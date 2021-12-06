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
import com.example.kipmnotes.adapter.PlacementRecyclerAdapter
import com.example.kipmnotes.adapter.RoadmapRecyclerAdapter


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class RoadmapFragment : Fragment() {


    private var param1: String? = null
    private var param2: String? = null

    lateinit var recyclerRoadmaps: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var recyclerAdapter: RoadmapRecyclerAdapter

    val roadmapTopicList = arrayListOf<String>(
        "  Web Development Roadmap",
        "  Android Development Roadmap",
        "  Machine Learning Roadmap",
        "  Data Science Roadmap"
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
        val view = inflater.inflate(R.layout.fragment_roadmap, container, false)

        recyclerRoadmaps = view.findViewById(R.id.recyclerRoadmap)
        layoutManager = LinearLayoutManager(activity)

//      Added Divider b/w items
        recyclerRoadmaps.addItemDecoration(
            DividerItemDecoration(
                recyclerRoadmaps.context,(layoutManager as LinearLayoutManager).orientation
            )
        )

        recyclerAdapter = RoadmapRecyclerAdapter(activity as Context,roadmapTopicList)
        recyclerRoadmaps.adapter = recyclerAdapter
        recyclerRoadmaps.layoutManager = layoutManager

        return view
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            RoadmapFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}