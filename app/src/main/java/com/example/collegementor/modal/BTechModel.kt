package com.example.collegementor.modal

data class BTechModel(
    val branch: String = "",
    val notesFile: HashMap<String, String> = hashMapOf("" to ""),
    val subjectName: String = "",
    val year: String = "",
    var isExpanded: Boolean = false
)
