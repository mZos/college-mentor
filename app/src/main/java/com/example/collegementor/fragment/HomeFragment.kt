package com.example.collegementor.fragment

import android.os.Bundle
import android.view.View
import com.example.collegementor.databinding.FragmentHomeBinding
import com.example.collegementor.fragment.basefragment.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}