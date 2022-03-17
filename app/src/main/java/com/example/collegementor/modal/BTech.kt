package com.example.collegementor.modal

data class BTech(
    val branch: String="",
    val fileDownloadLink: String="",
    val fileName: ArrayList<String> = arrayListOf(""),
    val subjectName: String="",
    val year: String="",
    var isExpanded: Boolean = false
)
