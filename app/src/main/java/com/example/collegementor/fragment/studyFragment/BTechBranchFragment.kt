package com.example.collegementor.fragment.studyFragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.collegementor.adapter.BTechBranchRecyclerAdapter
import com.example.collegementor.databinding.FragmentBtechBranchBinding
import com.example.collegementor.fragment.basefragment.BaseFragment

class BTechBranchFragment : BaseFragment<FragmentBtechBranchBinding>(
    FragmentBtechBranchBinding::inflate
) {
    private val branchList = arrayListOf(
        " CSE",
        " ECE",
        " EE",
        " ME",
        " CE"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val onBranchClickListener = BTechBranchRecyclerAdapter.OnClickListener { position ->
            when (branchList[position]) {
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