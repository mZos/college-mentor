package com.example.collegementor.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.collegementor.adapter.GuidedPathsRecyclerAdapter
import com.example.collegementor.databinding.FragmentGuidedPathsBinding
import com.example.collegementor.ui.fragment.basefragment.BaseFragment

class GuidedPathsFragment : BaseFragment<FragmentGuidedPathsBinding>(
    FragmentGuidedPathsBinding::inflate
) {

    val guidedPathsTopicList = arrayListOf(
        "Database Management System(DBMS)",
        "Object Oriented Programming",
        "Machine Learning",
        "Data Science",
        "Android Development",
        " Development",
        "React Development",
        " Development",
        "Angular Development",
        "Blockchain Development"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerGuidedPaths.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = GuidedPathsRecyclerAdapter(requireContext(), guidedPathsTopicList)
        }


    }
}