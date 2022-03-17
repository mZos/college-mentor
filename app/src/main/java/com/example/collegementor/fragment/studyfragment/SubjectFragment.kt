package com.example.collegementor.fragment.studyfragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.collegementor.adapter.SubjectRecyclerAdapter
import com.example.collegementor.databinding.FragmentSubjectBinding
import com.example.collegementor.firebase.Firebase.btechFirestoreRef
import com.example.collegementor.fragment.basefragment.BaseFragment
import com.example.collegementor.modal.BTech
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class SubjectFragment : BaseFragment<FragmentSubjectBinding>(FragmentSubjectBinding::inflate) {

//    private val subjectFileList = arrayListOf<Subject>(
//        Subject("Syllabus", "Syllabus"),
//        Subject("Cloud Computing", "Cloud Computing"),
//        Subject("Distributed System", "Distributed System"),
//        Subject("Rural Development and Planning", "Rural Development and Planning"),
//        Subject("Renewable Energy Resources", "Renewable Energy Resources")
//    )

    private var subjectList = arrayListOf<BTech>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        (binding.rvSubjectName.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

        retrieveBtechDB()
        Log.i("SubjectFrag36", subjectList.toString())

    }

    private fun retrieveBtechDB() = CoroutineScope(Dispatchers.IO).launch {

        try {
            val snapshot = btechFirestoreRef.get().await()
            for (document in snapshot.documents) {
                val btech = document.toObject<BTech>()
                btech?.let { subjectList.add(it) }

                withContext(Dispatchers.Main) {
                    setUpRecyclerView()
                }
            }
        } catch (e: Exception) {
            Log.e("SubjectFrag", e.message.toString())
        }
    }

    private fun setUpRecyclerView() = binding.rvSubjectName.apply {
        Log.e("SubjectFrag", "hello" + subjectList.toString())
        layoutManager = LinearLayoutManager(requireContext())
        adapter = SubjectRecyclerAdapter(requireContext(), subjectList)
    }


}