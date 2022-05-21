package com.example.collegementor.ui.languageActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment

import com.example.collegementor.databinding.FragmentCPPBinding
import java.io.BufferedReader

import java.io.InputStreamReader




class CPPFragment : Fragment() {

    private var _binding: FragmentCPPBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCPPBinding.inflate(inflater, container, false)
        val view = binding.root

        val `is` = resources.openRawResource(com.example.collegementor.R.raw.masoom)
        val br = BufferedReader(InputStreamReader(`is`))
        var line: String
        var entireFile = ""

        try {
            while((br.readLine().also { line = it }) != null) { // <--------- place readLine() inside loop
                entireFile += line + " "

            }
        } catch (e:Exception) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        binding.cppContent.text = entireFile

        return view
    }
}