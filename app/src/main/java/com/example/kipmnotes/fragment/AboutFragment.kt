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

class AboutFragment : Fragment() {

    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater, container, false)

        // Linked with linkedIn Profile
//        binding.txtAboutZakariya?.movementMethod = LinkMovementMethod.getInstance()
        binding.txtAboutZakariya?.setOnClickListener {
            Toast.makeText(activity as Context, "hello", Toast.LENGTH_SHORT).show()
        }
        binding.txtAboutMasoom?.movementMethod = LinkMovementMethod.getInstance()
        binding.txtAboutVishal?.movementMethod = LinkMovementMethod.getInstance()

        if (activity != null) {
            binding.txtAboutZakariya?.setLinkTextColor(
                ContextCompat.getColor(
                    activity as Context,
                    R.color.linkColor
                )
            )

            binding.txtAboutMasoom?.setLinkTextColor(
                ContextCompat.getColor(
                    activity as Context,
                    R.color.linkColor
                )
            )

            binding.txtAboutVishal?.setLinkTextColor(
                ContextCompat.getColor(
                    activity as Context,
                    R.color.linkColor
                )
            )
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtAboutZakariya?.text = "hello"
    }
}