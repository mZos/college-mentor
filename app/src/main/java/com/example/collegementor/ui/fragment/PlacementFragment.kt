package com.example.collegementor.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.collegementor.adapter.PlacementRecyclerAdapter
import com.example.collegementor.databinding.FragmentPlacementBinding
import com.example.collegementor.ui.fragment.basefragment.BaseFragment

class PlacementFragment : BaseFragment<FragmentPlacementBinding>(
    FragmentPlacementBinding::inflate
) {

    val placementTopicList = arrayListOf<String>(
        "  Data Structure",
        "  Algorithms",
        "  Aptitude",
        "  Interview Questions",
        "  Project Ideas"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerPlacement.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = PlacementRecyclerAdapter(requireContext(), placementTopicList)
        }
    }
}