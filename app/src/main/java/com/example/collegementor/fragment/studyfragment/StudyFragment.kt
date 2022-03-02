package com.example.collegementor.fragment.studyfragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.collegementor.R
import com.example.collegementor.adapter.StudyRecyclerAdapter
import com.example.collegementor.databinding.FragmentStudyBinding
import com.example.collegementor.fragment.basefragment.BaseFragment

class StudyFragment : BaseFragment<FragmentStudyBinding>(FragmentStudyBinding::inflate) {

    val studyTopicList = arrayListOf(
        "  B.Tech",
        "  B.Pharma",
        "  Diploma",
        "  MBA",
        "  BCA",
        "  BBA"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        binding.recyclerStudy.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = StudyRecyclerAdapter(
                requireContext(),
                studyTopicList,
                recyclerItemClickListener
            )
        }
    }
}