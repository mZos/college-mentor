package com.example.collegementor.fragment

import android.content.Context
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.collegementor.R
import com.example.collegementor.databinding.FragmentAboutBinding

class AboutFragment : Fragment(R.layout.fragment_about) {

    private lateinit var binding: FragmentAboutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAboutBinding.bind(view)

        binding.txtAboutZakariya.movementMethod = LinkMovementMethod.getInstance()
        binding.txtAboutMasoom.movementMethod = LinkMovementMethod.getInstance()
        binding.txtAboutVishal.movementMethod = LinkMovementMethod.getInstance()

        if (activity != null) {
            binding.txtAboutZakariya.setLinkTextColor(
                ContextCompat.getColor(
                    activity as Context,
                    R.color.linkColor
                )
            )

            binding.txtAboutMasoom.setLinkTextColor(
                ContextCompat.getColor(
                    activity as Context,
                    R.color.linkColor
                )
            )

            binding.txtAboutVishal.setLinkTextColor(
                ContextCompat.getColor(
                    activity as Context,
                    R.color.linkColor
                )
            )
        }
    }
}