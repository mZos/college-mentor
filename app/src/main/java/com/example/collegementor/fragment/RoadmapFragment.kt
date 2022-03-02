package com.example.collegementor.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.collegementor.adapter.RoadmapRecyclerAdapter
import com.example.collegementor.databinding.FragmentRoadmapBinding
import com.example.collegementor.fragment.basefragment.BaseFragment

class RoadmapFragment : BaseFragment<FragmentRoadmapBinding>(
    FragmentRoadmapBinding::inflate
) {

    val roadmapTopicList = arrayListOf(
        "  Web Development Roadmap",
        "  Android Development Roadmap",
        "  Machine Learning Roadmap",
        "  Data Science Roadmap"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerRoadmap.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = RoadmapRecyclerAdapter(requireContext(), roadmapTopicList)
        }
    }
}