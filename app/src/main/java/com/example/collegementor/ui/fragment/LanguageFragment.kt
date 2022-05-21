package com.example.collegementor.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.collegementor.adapter.LanguageRecyclerAdapter
import com.example.collegementor.databinding.FragmentLanguageBinding
import com.example.collegementor.ui.fragment.basefragment.BaseFragment
import com.example.collegementor.ui.languageActivity.LanguageActivity

class LanguageFragment : BaseFragment<FragmentLanguageBinding>(
    FragmentLanguageBinding::inflate
), LanguageRecyclerAdapter.OnLanguageClickListener {

    val languageList = arrayListOf(
        "  Learn Python",
        "  Learn Java",
        "  Learn C++",
        "  Learn Javascript",
        "  Learn C",
        "  Learn SQL",
        "  Learn R"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerLanguage.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = LanguageRecyclerAdapter(requireContext(), languageList, this@LanguageFragment)
        }
    }

    override fun onLanguageClick(position: Int) {
        Toast.makeText(context, "item $position", Toast.LENGTH_LONG).show()
        activity?.let {
            val intent = Intent(it, LanguageActivity::class.java)
            intent.putExtra("position", position)
            it.startActivity(intent)
        }
    }

}