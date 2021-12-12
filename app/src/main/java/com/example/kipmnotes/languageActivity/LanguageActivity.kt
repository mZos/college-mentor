package com.example.kipmnotes.languageActivity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.kipmnotes.R
import com.example.kipmnotes.databinding.ActivityLanguageBinding
import com.example.kipmnotes.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

class LanguageActivity : AppCompatActivity() {

    lateinit var binding:ActivityLanguageBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        firebaseAuth = Firebase.mAuth
        setUpToolbar()

        val position = intent.getIntExtra("position",-1)

//      intent according to positions
        when(position){

            0 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.languageFrame,PythonFragment())
                    .commit()
                supportActionBar?.title = "Learn Python"
            }

            2 -> {
                 supportFragmentManager.beginTransaction()
                     .replace(R.id.languageFrame,CPPFragment())
                     .commit()
                supportActionBar?.title = "Learn C++"
            }
        }

    }

    //  Creating an function for setting up toolbar with hamburger icon
    private fun setUpToolbar() {
        setSupportActionBar(binding.languageToolbar)
        supportActionBar?.title = "Languages"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    //  setting up hamburger icon actions
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}