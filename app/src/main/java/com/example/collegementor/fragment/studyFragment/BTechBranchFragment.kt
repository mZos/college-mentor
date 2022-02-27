package com.example.collegementor.fragment.studyFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.collegementor.R
import com.example.collegementor.adapter.BTechBranchRecyclerAdapter
import com.example.collegementor.databinding.FragmentBtechBranchBinding

class BTechBranchFragment: Fragment(R.layout.fragment_btech_branch) {

    private lateinit var binding: FragmentBtechBranchBinding
    private val branchList = arrayListOf(
        " CSE",
        " ECE",
        " EE",
        " ME",
        " CE"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBtechBranchBinding.bind(view)

       binding.recylerBtech.apply {
           adapter = BTechBranchRecyclerAdapter(branchList)
           layoutManager = LinearLayoutManager(requireContext())
       }

    }
}