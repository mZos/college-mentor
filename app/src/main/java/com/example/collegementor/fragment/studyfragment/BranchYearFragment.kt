package com.example.collegementor.fragment.studyfragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.collegementor.adapter.BranchYearRecyclerAdapter
import com.example.collegementor.databinding.FragmentBranchYearBinding
import com.example.collegementor.fragment.basefragment.BaseFragment

class BranchYearFragment : BaseFragment<FragmentBranchYearBinding>(
    FragmentBranchYearBinding::inflate
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val branchYearClickListener = BranchYearRecyclerAdapter.OnClickListener { position ->
            when (position) {
                0 -> {
                    Toast.makeText(requireContext(), "1st Year notes coming soon", Toast.LENGTH_SHORT).show()
                }
                1 -> {
                    Toast.makeText(requireContext(), "2nd Year notes coming soon", Toast.LENGTH_SHORT).show()
                }
                2 -> {
                    Toast.makeText(requireContext(), "3rd Year notes coming soon", Toast.LENGTH_SHORT).show()
                }
                3 -> {
                    Toast.makeText(requireContext(), "4th Year notes coming soon", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.recylerBranchYear.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = BranchYearRecyclerAdapter(branchYearClickListener)
        }
    }
}