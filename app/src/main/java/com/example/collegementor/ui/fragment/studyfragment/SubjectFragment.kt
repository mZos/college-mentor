package com.example.collegementor.ui.fragment.studyfragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.collegementor.ui.activity.PdfViewerActivity
import com.example.collegementor.adapter.SubjectNestedItemAdapter
import com.example.collegementor.adapter.SubjectRecyclerAdapter
import com.example.collegementor.databinding.FragmentSubjectBinding
import com.example.collegementor.firebase.Firebase.btechCs1FirestoreRef
import com.example.collegementor.firebase.Firebase.btechCs2FirestoreRef
import com.example.collegementor.firebase.Firebase.btechCs3FirestoreRef
import com.example.collegementor.firebase.Firebase.btechCs4FirestoreRef
import com.example.collegementor.ui.fragment.basefragment.BaseFragment
import com.example.collegementor.modal.BTechModel
import com.example.collegementor.utils.Constants.YEAR_KEY
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class SubjectFragment : BaseFragment<FragmentSubjectBinding>(FragmentSubjectBinding::inflate),
    SubjectNestedItemAdapter.OnSubjectFileClickListener {

    private var subjectList = arrayListOf<BTechModel>()
    private lateinit var year: String
    private lateinit var firestoreRef: CollectionReference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            year = it.getString(YEAR_KEY, "")
        }
        when (year) {
            "4" -> {
                firestoreRef = btechCs4FirestoreRef
            }
            "3" -> {
                firestoreRef = btechCs3FirestoreRef
            }
            "2" -> {
                firestoreRef = btechCs2FirestoreRef
            }
            "1" -> {
                firestoreRef = btechCs1FirestoreRef
            }
            else -> {

            }
        }

        retrieveBTechDB(firestoreRef)
    }

    private fun retrieveBTechDB(firestoreRef: CollectionReference) =
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val snapshot = firestoreRef.get().await()
                snapshot.documents.forEach { data ->
                    val bTech = data.toObject<BTechModel>()
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
        Intent(requireActivity(), PdfViewerActivity::class.java).also {
            it.putExtra("downloadLink", downloadLink)
            startActivity(it)
        }
    }
}