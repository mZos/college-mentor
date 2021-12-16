package com.example.collegementor.languageActivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.collegementor.R
import com.example.collegementor.databinding.FragmentPythonBinding
import java.io.BufferedReader
import java.io.InputStreamReader

class PythonFragment : Fragment() {

    private var _binding: FragmentPythonBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPythonBinding.inflate(inflater, container, false)
        val view = binding.root

        val content = resources.openRawResource(R.raw.pythonotes)
        val br = BufferedReader(InputStreamReader(content))
        var line: String
        var entireFile = " "

        try {
            while((br.readLine().also { line = it }) != null) {
                entireFile += line + "\n"
            }
        } catch (e:Exception) {
            e.printStackTrace();
        }
        binding.pythonContent.text = entireFile

        return view
    }

}