package com.example.collegementor.fragment.studyfragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.collegementor.adapter.SubjectRecyclerAdapter
import com.example.collegementor.databinding.FragmentSubjectBinding
import com.example.collegementor.fragment.basefragment.BaseFragment

class SubjectFragment: BaseFragment<FragmentSubjectBinding>(FragmentSubjectBinding::inflate) {

    private val subjectNameList = arrayListOf(
        "Syllabus",
        "Cloud Computing",
        "Distributed System",
        "Rural Development and Planning",
        "Renewable Energy Resources"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvSubjectName.apply {
            layoutManager=LinearLayoutManager(requireContext())
            adapter = SubjectRecyclerAdapter(subjectNameList)
        }
    }
}