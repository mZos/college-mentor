package com.example.collegementor.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

object Firebase {
    val mAuth = FirebaseAuth.getInstance()
    val btechFirestoreRef = Firebase.firestore.collection("b.tech")
    val storage = FirebaseStorage.getInstance()
}