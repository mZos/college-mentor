package com.example.collegementor.fragment

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.example.collegementor.R
import com.example.collegementor.databinding.DialogAddSubjectsBinding
import com.example.collegementor.firebase.Firebase
import com.example.collegementor.firebase.Firebase.firestoreRef
import com.example.collegementor.firebase.Firebase.storageRef

class FragmentCustomDialogAddSubjects : DialogFragment(R.layout.dialog_add_subjects),
    View.OnClickListener {

    private lateinit var binding: DialogAddSubjectsBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var fileUriList: ArrayList<Uri>? = null
    private lateinit var subject: HashMap<String, Any>
    private var fileData: HashMap<String, String>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogAddSubjectsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSelectNotes.setOnClickListener(this)
        binding.btnUpload.setOnClickListener(this)
        binding.btnUpload.visibility = View.GONE

        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                it.data?.let { data ->
                    if (data.clipData != null) {
                        for (i in 0 until data.clipData!!.itemCount) {
                            val fileUri = data.clipData!!.getItemAt(i).uri
                            fileUriList?.add(fileUri)
                        }
                    } else {
                        fileUriList?.add(data.data!!)
                    }
                }

                if (!fileUriList.isNullOrEmpty()) {
                    binding.btnUpload.visibility = View.VISIBLE
                } else {
                    binding.btnUpload.visibility = View.GONE
                }

                var fileNames = ""
                fileUriList!!.forEach { fileUri ->
                    fileNames += if (getFileName(fileUri).length >= 40)
                        "• " + getFileName(fileUri).subSequence(0, 30) + "...\n"
                    else
                        "• " + getFileName(fileUri) + "\n"
                }

                binding.txtFileName.visibility = View.VISIBLE
                binding.txtFileName.text = fileNames
            }
    }


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnSelectNotes -> {
                Intent().also {
                    fileUriList = arrayListOf()
                    fileData = null
                    it.type = "application/pdf"
                    it.action = Intent.ACTION_GET_CONTENT
                    it.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                    it.putExtra(Intent.ACTION_GET_CONTENT, true)
                    resultLauncher.launch(it)
                }
            }

            R.id.btnUpload -> {
                var isNullOrEmpty = false
                if (binding.etSubjectName.text.isNullOrEmpty()) {
                    binding.etSubjectName.error = "Required"
                    isNullOrEmpty = true
                }
                if (binding.etYear.text.isNullOrEmpty()) {
                    binding.etYear.error = "Required"
                    isNullOrEmpty = true
                }
                if (binding.etBranch.text.isNullOrEmpty()) {
                    binding.etBranch.error = "Required"
                    isNullOrEmpty = true
                }

                if (!isNullOrEmpty) {
                    binding.btnUpload.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE

                    uploadFilesIntoCloud()
                }
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        if (!fileUriList.isNullOrEmpty()) {
            fileUriList = null
            fileData = null
        }
    }

    private fun uploadFilesIntoCloud() {
        if (!fileUriList.isNullOrEmpty()) {
            fileUriList!!.forEach { fileUri ->
                if (getFileName(fileUri).length == 2) {
                    binding.btnUpload.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    toast("Error: Select from internal storage")
                    binding.btnUpload.text = getString(R.string.retry)
                } else {
                    binding.btnSelectNotes.isClickable = false
                    fileData = hashMapOf()
                    //upload files into cloud storage
                    val storageRef = storageRef.child(getRefPath(getFileName(fileUri)))
                    val fileUploadTask = storageRef.putFile(fileUri)
                    //on files uploaded successfully into cloud storage
                    fileUploadTask.addOnSuccessListener { snapShot ->
                        //get file download url to store in firestore document
                        val getFileDownloadUrl =
                            Firebase.storageRef.child(snapShot.metadata!!.path).downloadUrl
                        getFileDownloadUrl.addOnSuccessListener { fileUrl ->
                            //map file-url to corresponding file-name
                            fileData?.set(getFileName(fileUri), fileUrl.toString())

                            //map object for firestore document
                            subject = hashMapOf(
                                "branch" to binding.etBranch.text.toString(),
                                "notesFile" to fileData!!,
                                "subjectName" to binding.etSubjectName.text.toString(),
                                "year" to binding.etYear.text.toString()
                            )

                            //creating firestore doc for uploaded file
                            uploadDataIntoFirestore(subject, fileUri)
                        }
                        getFileDownloadUrl.addOnFailureListener {
                            it.printStackTrace()
                            binding.btnUpload.visibility = View.VISIBLE
                            binding.progressBar.visibility = View.GONE
                        }

                        binding.btnUpload.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE

                        fileUriList!!.remove(fileUri)
                        binding.btnSelectNotes.isClickable = true
                    }
                    fileUploadTask.addOnFailureListener { e ->
                        e.printStackTrace()
                        binding.btnSelectNotes.isClickable = true
                        toast(e.message)
                        binding.btnUpload.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        } else {
            binding.progressBar.visibility = View.GONE
            binding.btnUpload.visibility = View.VISIBLE
            toast("Select some files to upload")
        }
    }

    private fun uploadDataIntoFirestore(data: HashMap<String, Any>, uri: Uri) {
        firestoreRef.collection(getCollectionName())
            .document(binding.etSubjectName.text.toString())
            .set(data)
            .addOnSuccessListener {
                toast("Uploaded Successfully - ${getFileName(uri)}")
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
                binding.btnSelectNotes.isClickable = true
                toast(e.message)
            }
    }

    private fun getRefPath(fileName: String): String {
        return when (binding.etYear.text.toString()) {
            "4" -> {
                "btech_cse_4/${binding.etSubjectName.text}/$fileName"
            }
            "3" -> {
                "btech_cse_3/${binding.etSubjectName.text}/$fileName"
            }
            "2" -> {
                "btech_cse_2/${binding.etSubjectName.text}/$fileName"
            }
            "1" -> {
                "btech_cse_1/${binding.etSubjectName.text}/$fileName"
            }
            else -> {
                "pdf/$fileName"
            }
        }
    }

    private fun getFileName(uri: Uri): String {
        return if (uri.lastPathSegment!!.contains("/")) {
            uri.lastPathSegment!!.split("/").last()
        } else {
            uri.lastPathSegment!!
        }
    }

    private fun getCollectionName(): String {
        return when (binding.etYear.text.toString()) {
            "4" -> {
                "btech_cs_4"
            }
            "3" -> {
                "btech_cs_3"
            }
            "2" -> {
                "btech_cs_2"
            }
            "1" -> {
                "btech_cs_1"
            }
            else -> {
                "unknown"
            }
        }
    }

    private fun toast(m: String?) {
        Toast.makeText(context, m.toString(), Toast.LENGTH_SHORT).show()
    }
}