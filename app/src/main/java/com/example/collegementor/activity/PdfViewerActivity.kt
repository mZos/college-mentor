package com.example.collegementor.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.collegementor.databinding.ActivityPdfViewerBinding
import com.example.collegementor.firebase.Firebase.storage
import com.example.collegementor.utils.Constants.HALF_GIGABYTE
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class PdfViewerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPdfViewerBinding
    private lateinit var fileDownloadLink: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPdfViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent?.let {
            fileDownloadLink = it.getStringExtra("downloadLink")!!
        }

        val fileRefFromUrl = storage.getReferenceFromUrl(fileDownloadLink)

        binding.pdfView.visibility = View.GONE
        lifecycleScope.launch(Dispatchers.IO) {
            val byteArray = fileRefFromUrl.getBytes(HALF_GIGABYTE).await()
            withContext(Dispatchers.Main) {
                binding.progressBar.visibility = View.GONE
                binding.pdfView.visibility = View.VISIBLE
                binding.pdfView.fromBytes(byteArray)
                    .defaultPage(0)
                    .spacing(6)
                    .swipeHorizontal(false)
                    .scrollHandle(DefaultScrollHandle(this@PdfViewerActivity))
                    .load()
            }
        }
    }
}