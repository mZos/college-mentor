package com.example.collegementor.modal

data class Subject(
    val subjectName: String,
    val fileName: String = "Notes will be coming soon",
    var isExpanded: Boolean = false
)
