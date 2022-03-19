package com.example.collegementor.fragment.studyfragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.collegementor.adapter.SubjectNestedItemAdapter
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

class SubjectFragment : BaseFragment<FragmentSubjectBinding>(FragmentSubjectBinding::inflate),
    SubjectNestedItemAdapter.OnSubjectFileClickListener {

    private var subjectList = arrayListOf<BTech>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retrieveBTechDB()
    }

    private fun retrieveBTechDB() = CoroutineScope(Dispatchers.IO).launch {
        try {
            val snapshot = btechFirestoreRef.get().await()
            snapshot.documents.forEach { data ->
                val bTech = data.toObject<BTech>()
                bTech?.let { subjectList.add(it) }
            }
            withContext(Dispatchers.Main) {
                setUpRecyclerView()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setUpRecyclerView() = binding.rvSubjectName.apply {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = SubjectRecyclerAdapter(requireContext(), subjectList, this@SubjectFragment)
    }

    override fun onFileClickListener(downloadLink: String) {
        Toast.makeText(requireContext(), downloadLink, Toast.LENGTH_SHORT).show()
    }
}