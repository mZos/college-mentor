package com.example.collegementor.fragment.studyFragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.collegementor.R
import com.example.collegementor.adapter.BTechBranchRecyclerAdapter
import com.example.collegementor.databinding.FragmentBtechBranchBinding

class BTechBranchFragment : Fragment(R.layout.fragment_btech_branch) {

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

        val onBranchClickListener = BTechBranchRecyclerAdapter.OnClickListener { position ->
            when(branchList[position]) {
                " CSE" -> {
                    toast("${branchList[position]} notes coming soon")
                }
                " ECE" -> {
                    toast("${branchList[position]} notes coming soon")
                }
                " EE" -> {
                    toast("${branchList[position]} notes coming soon")
                }
                " ME" -> {
                    toast("${branchList[position]} notes coming soon")
                }
                " CE" -> {
                    toast("${branchList[position]} notes coming soon")
                }
            }
        }

        binding.recylerBtech.apply {
            adapter = BTechBranchRecyclerAdapter(branchList, onBranchClickListener)
            layoutManager = LinearLayoutManager(requireContext())
        }

    }

    private fun toast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}