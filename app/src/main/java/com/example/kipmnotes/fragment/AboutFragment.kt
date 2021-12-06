package com.example.kipmnotes.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kipmnotes.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AboutFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    lateinit var aboutMasoom:TextView
    lateinit var aboutZakariya:TextView
    lateinit var aboutVishal:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_about,container,false)


        aboutMasoom = view.findViewById(R.id.aboutMasoom)
        aboutZakariya = view.findViewById(R.id.aboutZakariya)
        aboutVishal = view.findViewById(R.id.aboutVishal)

        // Linked with linkdin Profile
        aboutMasoom.setMovementMethod(LinkMovementMethod.getInstance())
        aboutMasoom.setLinkTextColor(R.color.linkColor)
        aboutZakariya.setMovementMethod(LinkMovementMethod.getInstance())
        aboutZakariya.setLinkTextColor(R.color.linkColor)
        aboutVishal.setMovementMethod(LinkMovementMethod.getInstance())
        aboutVishal.setLinkTextColor(R.color.linkColor)
        
        return  view
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            AboutFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}