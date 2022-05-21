package com.example.collegementor.ui.fragment

import android.content.Context
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.core.content.ContextCompat
import com.example.collegementor.R
import com.example.collegementor.databinding.FragmentAboutBinding
import com.example.collegementor.ui.fragment.basefragment.BaseFragment

class AboutFragment : BaseFragment<FragmentAboutBinding>(
    FragmentAboutBinding::inflate
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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