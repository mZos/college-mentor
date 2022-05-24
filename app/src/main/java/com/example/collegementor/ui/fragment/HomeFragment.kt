package com.example.collegementor.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.collegementor.R
import com.example.collegementor.adapter.HomeRecyclerAdapter
import com.example.collegementor.databinding.FragmentHomeBinding
import com.example.collegementor.modal.HomeModel
import com.example.collegementor.ui.fragment.basefragment.BaseFragment


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemList = arrayListOf<HomeModel>(
            HomeModel(ContextCompat.getDrawable(requireContext(), R.drawable.ic_home), "Home"),
            HomeModel(ContextCompat.getDrawable(requireContext(), R.drawable.ic_study), "Study"),
            HomeModel(ContextCompat.getDrawable(requireContext(), R.drawable.ic_placement), "Placements"),
            HomeModel(ContextCompat.getDrawable(requireContext(), R.drawable.ic_roadmap), "Roadmaps"),
            HomeModel(ContextCompat.getDrawable(requireContext(), R.drawable.ic_language), "Programming"),
            HomeModel(ContextCompat.getDrawable(requireContext(), R.drawable.ic_skill), "Extras"),
            HomeModel(ContextCompat.getDrawable(requireContext(), R.drawable.ic_interview), "Interview"),
            HomeModel(ContextCompat.getDrawable(requireContext(), R.drawable.ic_about), "About Us"),
        )

        binding.rvHome.adapter = HomeRecyclerAdapter(requireContext(), itemList)
    }
}