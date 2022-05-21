package com.example.collegementor.ui.fragment.studyfragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.collegementor.R
import com.example.collegementor.ui.activity.HomeActivity
import com.example.collegementor.adapter.StudyRecyclerAdapter
import com.example.collegementor.databinding.FragmentStudyBinding
import com.example.collegementor.ui.fragment.basefragment.BaseFragment
import com.example.collegementor.utils.Constants.BRANCH_KEY
import com.example.collegementor.utils.Constants.COURSE_KEY
import com.example.collegementor.utils.Constants.YEAR_KEY

class StudyFragment : BaseFragment<FragmentStudyBinding>(FragmentStudyBinding::inflate) {

    private val courseNameList = arrayListOf(
        "B.Tech",
        "B.Pharma",
        "Diploma",
        "MBA",
        "BCA",
        "BBA"
    )

    private val branchList = arrayListOf(
        "CSE",
        "ECE",
        "EE",
        "ME",
        "CE"
    )

    private val yearList = arrayListOf(
        "1st Year",
        "2nd Year",
        "3rd Year",
        "4th Year"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val onCourseClickListener = StudyRecyclerAdapter.OnClickListener { itemPosition ->
            when (val course = courseNameList[itemPosition]) {
                "B.Tech" -> {
                    log(49)
                    refreshFragment(COURSE_KEY, course)
                }
                "B.Pharma" -> {
                    toast("Notes for $course Coming soon")
                }
                "Diploma" -> {
                    toast("Notes for $course Coming soon")
                }
                "MBA" -> {
                    toast("Notes for $course Coming soon")
                }
                "BCA" -> {
                    toast("Notes for $course Coming soon")
                }
                "BBA" -> {
                    toast("Notes for $course Coming soon")
                }
            }
        }

        val onBranchClickListener = StudyRecyclerAdapter.OnClickListener { position ->
            when (val branch = branchList[position]) {
                "CSE" -> {
                    log(73)
                    refreshFragment(BRANCH_KEY, branch)
                }
                "ECE" -> {
                    toast("Notes for $branch Coming soon")
                }
                "EE" -> {
                    toast("Notes for $branch Coming soon")
                }
                "ME" -> {
                    toast("Notes for $branch Coming soon")
                }
                "CE" -> {
                    toast("Notes for $branch Coming soon")
                }
            }
        }

        val onYearClickListener = StudyRecyclerAdapter.OnClickListener { position ->
            when (yearList[position]) {
                "1st Year" -> {
                    openSubjectFragment("1")
                }
                "2nd Year" -> {
                    openSubjectFragment("2")
                }
                "3rd Year" -> {
                    openSubjectFragment("3")
                }
                "4th Year" -> {
                    openSubjectFragment("4")
                }
            }
        }

        //default recycler and actionBar title with course names
        if (arguments == null) {
            setUpRecyclerViewAdapter(courseNameList, onCourseClickListener)
            getActionBar()?.let {
                if (it.title != "Select course")
                    it.title = "Select course"
            }
        }

        //updating recycler and actionBar title with branch name list
        when (arguments?.getString(COURSE_KEY)) {
            courseNameList[0] -> {
                setUpRecyclerViewAdapter(branchList, onBranchClickListener)
                getActionBar()?.let {
                    it.title = "Select Branch"
                }
            }
            courseNameList[1] -> {}
            courseNameList[2] -> {}
            courseNameList[3] -> {}
        }

        //updating recycler and actionBar title with year list
        when (arguments?.getString(BRANCH_KEY)) {
            branchList[0] -> {
                setUpRecyclerViewAdapter(yearList, onYearClickListener)
                getActionBar()?.let {
                    it.title = "Select Year"
                }
            }
            branchList[1] -> {}
            branchList[2] -> {}
            branchList[3] -> {}
            branchList[4] -> {}
        }
    }

    private fun getActionBar(): ActionBar? {
        return (activity as HomeActivity).supportActionBar
    }

    private fun setUpRecyclerViewAdapter(
        titleList: ArrayList<String>,
        recyclerItemClickListener: StudyRecyclerAdapter.OnClickListener
    ) = binding.recyclerStudy.apply {
        val mAdapter = StudyRecyclerAdapter(titleList, recyclerItemClickListener)
        layoutManager = LinearLayoutManager(requireContext())
        adapter = mAdapter
    }

    private fun refreshFragment(key: String?, value: String) {
        val bundle = Bundle()
        val fragment = StudyFragment()
        bundle.putString(key, value)
        fragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.frame, fragment)
            .addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }

    private fun openSubjectFragment(year:String) {
        val bundle= Bundle()
        bundle.putString(YEAR_KEY, year)
        val subjectFragment = SubjectFragment()
        subjectFragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.frame, subjectFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }

    fun log(v: Any) {
        Log.i("Study", v.toString())
    }

    private fun toast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}