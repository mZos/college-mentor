package com.example.kipmnotes.fragment

import android.content.Context
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.kipmnotes.R
import com.example.kipmnotes.databinding.FragmentAboutBinding

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