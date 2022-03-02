package com.example.collegementor.fragment.studyfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.collegementor.R
import com.example.collegementor.adapter.StudyRecyclerAdapter

class StudyFragment : Fragment() {

    lateinit var recyclerStudy: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var recyclerAdapter: StudyRecyclerAdapter

    val studyTopicList = arrayListOf<String>(
        "  B.Tech",
        "  B.Pharma",
        "  Diploma",
        "  MBA",
        "  BCA",
        "  BBA"
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_study, container, false)

        recyclerStudy = view.findViewById(R.id.recyclerStudy)
        layoutManager = LinearLayoutManager(activity)

        val recyclerItemClickListener = StudyRecyclerAdapter.OnClickListener { itemPosition ->

            when (studyTopicList[itemPosition]) {
                "  B.Tech" -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, BTechBranchFragment())
                        .addToBackStack(null)
                        .commit()
                    (activity as AppCompatActivity).supportActionBar?.title = "Select Branch"
                }
                "  B.Pharma" -> {
                    Toast.makeText(
                        requireContext(),
                        studyTopicList[itemPosition],
                        Toast.LENGTH_SHORT
                    ).show()
                }
                "  Diploma" -> {
                    Toast.makeText(
                        requireContext(),
                        studyTopicList[itemPosition],
                        Toast.LENGTH_SHORT
                    ).show()
                }
                "  MBA" -> {
                    Toast.makeText(
                        requireContext(),
                        studyTopicList[itemPosition],
                        Toast.LENGTH_SHORT
                    ).show()
                }
                "  BCA" -> {
                    Toast.makeText(
                        requireContext(),
                        studyTopicList[itemPosition],
                        Toast.LENGTH_SHORT
                    ).show()
                }
                "  BBA" -> {
                    Toast.makeText(
                        requireContext(),
                        studyTopicList[itemPosition],
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        recyclerAdapter =
            StudyRecyclerAdapter(activity as Context, studyTopicList, recyclerItemClickListener)
        recyclerStudy.adapter = recyclerAdapter
        recyclerStudy.layoutManager = layoutManager

        return view
    }
}