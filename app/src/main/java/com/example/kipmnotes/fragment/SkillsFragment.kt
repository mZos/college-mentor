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
import com.example.kipmnotes.adapter.RoadmapRecyclerAdapter
import com.example.kipmnotes.adapter.SkillRecyclerAdapter

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SkillsFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    lateinit var recyclerSkills: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var recyclerAdapter: SkillRecyclerAdapter

    val skillsTopicList = arrayListOf<String>(
        "  Learn Git & Github",
        "  Learn VSCODE Tricks",
        "  Learn Command Tricks",
        "  Learn Linux"
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
        val view = inflater.inflate(R.layout.fragment_skills, container, false)

        recyclerSkills = view.findViewById(R.id.recyclerSkills)
        layoutManager = LinearLayoutManager(activity)

//      Added Divider b/w items
        recyclerSkills.addItemDecoration(
            DividerItemDecoration(
                recyclerSkills.context,(layoutManager as LinearLayoutManager).orientation
            )
        )

        recyclerAdapter = SkillRecyclerAdapter(activity as Context,skillsTopicList)
        recyclerSkills.adapter = recyclerAdapter
        recyclerSkills.layoutManager = layoutManager

        return view
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            SkillsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}