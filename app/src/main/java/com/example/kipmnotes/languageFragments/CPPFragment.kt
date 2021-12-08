package com.example.kipmnotes.languageFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kipmnotes.R
import com.example.kipmnotes.databinding.FragmentCPPBinding

class CPPFragment : Fragment() {

    private var _binding: FragmentCPPBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCPPBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
}