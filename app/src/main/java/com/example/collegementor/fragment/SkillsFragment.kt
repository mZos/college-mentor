package com.example.collegementor.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.collegementor.adapter.SkillRecyclerAdapter
import com.example.collegementor.databinding.FragmentSkillsBinding
import com.example.collegementor.fragment.basefragment.BaseFragment

class SkillsFragment : BaseFragment<FragmentSkillsBinding>(FragmentSkillsBinding::inflate) {
    val skillsTopicList = arrayListOf(
        "  Learn Git & Github",
        "  Learn VSCODE Tricks",
        "  Learn Command Tricks",
        "  Learn Linux"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerSkills.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = SkillRecyclerAdapter(requireContext(), skillsTopicList)
        }
    }
}