package com.example.collegementor.firebase

import android.annotation.SuppressLint
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

object Firebase {
    val mAuth = FirebaseAuth.getInstance()
    val btechCs1FirestoreRef = Firebase.firestore.collection("btech_cs_1")
    val btechCs2FirestoreRef = Firebase.firestore.collection("btech_cs_2")
    val btechCs3FirestoreRef = Firebase.firestore.collection("btech_cs_3")
    val btechCs4FirestoreRef = Firebase.firestore.collection("btech_cs_4")

    @SuppressLint("StaticFieldLeak")
    val firestoreRef: FirebaseFirestore = Firebase.firestore
    val storage = FirebaseStorage.getInstance()
    val storageRef = storage.reference
}