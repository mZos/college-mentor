package com.example.collegementor.fragment.studyfragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.collegementor.adapter.SubjectRecyclerAdapter
import com.example.collegementor.databinding.FragmentSubjectBinding
import com.example.collegementor.fragment.basefragment.BaseFragment
import com.example.collegementor.modal.Subject

class SubjectFragment : BaseFragment<FragmentSubjectBinding>(FragmentSubjectBinding::inflate) {

    private val subjectFileList = arrayListOf<Subject>(
        Subject("Syllabus", "Syllabus"),
        Subject("Cloud Computing", "Cloud Computing"),
        Subject("Distributed System", "Distributed System"),
        Subject("Rural Development and Planning", "Rural Development and Planning"),
        Subject("Renewable Energy Resources", "Renewable Energy Resources")
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        (binding.rvSubjectName.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

        binding.rvSubjectName.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = SubjectRecyclerAdapter(requireContext(), subjectFileList)
        }
    }
}