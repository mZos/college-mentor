package com.example.collegementor.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import com.example.collegementor.databinding.ActivityRegisterBinding
import com.example.collegementor.firebase.Firebase.mAuth
import com.google.firebase.auth.FirebaseAuth



class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var password : String
    private lateinit var email :String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        firebaseAuth = mAuth

        // Design Login text
        changeColorTextView()

        // function call for validations
        emailFocusListener()
        nameFocusListener()
        collegeNameFocusListener()
        courseNameFocusListener()
        passwordFocusListener()
        confirmPasswordFocusListener()



        // set on click listerner on again login button
        binding.txtAgainLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // set on click listerner on sign in button
        binding.btnRegister.setOnClickListener {
            firebaseRegister()
        }
    }

    //get data and create account using Firebase
    private fun firebaseRegister() {

        try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {

                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()

            }.addOnFailureListener {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Please fill entries", Toast.LENGTH_LONG).show()
        }
    }

    //    used for show errors on edittext when empty
    private fun setErrors(view: EditText, error: String) {
        view.error = error
    }

    // Change color for designing purpose
    private fun changeColorTextView() {
        val mText = binding.txtAgainLogin.text.toString()

        val mSpannableString = SpannableString(mText)
        val mRed = ForegroundColorSpan(Color.RED)

        mSpannableString.setSpan(mRed, 23, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.txtAgainLogin.text = mSpannableString
    }

//    Function for validations

//    validate email address
    private fun emailFocusListener(){

        binding.emailEditText.setOnFocusChangeListener { _, focused ->

            if (!focused){
                email = binding.emailEditText.text.toString()

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    setErrors(binding.emailEditText,"Invalid Email Address")
                }
            }
        }
    }


    // validate full name
    private fun nameFocusListener(){

        binding.nameEditText.setOnFocusChangeListener{_,focused ->

            if (!focused){
                val fullName = binding.nameEditText.text.toString().trim()

                if (fullName.isEmpty()){
                    setErrors(binding.nameEditText, "Enter Full Name")
                }
            }
        }
    }

    // validate college name
    private fun collegeNameFocusListener(){

        binding.collegeEditText.setOnFocusChangeListener{_,focused ->

            if (!focused){
                val collegeName = binding.collegeEditText.text.toString().trim()

                if (collegeName.isEmpty()){
                    setErrors(binding.collegeEditText, "Enter College Name")
                }
            }
        }
    }

    // validate course name
    private fun courseNameFocusListener(){

        binding.courseEditText.setOnFocusChangeListener{_,focused ->

            if (!focused){
                val courseName = binding.courseEditText.text.toString().trim()

                if (courseName.isEmpty()){
                    setErrors(binding.courseEditText, "Enter Course Name")
                }
            }
        }
    }

    // validate password
    private fun passwordFocusListener(){

        binding.passEditText.setOnFocusChangeListener{_,focused ->

            if (!focused){
                password = binding.passEditText.text.toString()
                val checkPass = "^" +
                        "(?=.*[0-9])" +   // at least 1 digit
                        "(?=.*[a-zA-Z])" +   // any letter
                        "(?=.*[@#$%^&+=])" +  // at least 1 spacial character
                        "(?=\\S+$)" +    // no white space
                        ".{8,}" +        // at least 8 character
                        "$"
                if (!password.matches(checkPass.toRegex())){
                    setErrors(binding.passEditText,"Enter Strong pasword")
                }
            }
        }
    }

    // validate confirm password
    private fun confirmPasswordFocusListener(){

        binding.confirmEditText.setOnFocusChangeListener{_,focused ->

            if (!focused){
                password = binding.passEditText.text.toString()
                val checkPass = "^" +
                        "(?=.*[0-9])" +   // at least 1 digit
                        "(?=.*[a-zA-Z])" +   // any letter
                        "(?=.*[@#$%^&+=])" +  // at least 1 spacial character
                        "(?=\\S+$)" +    // no white space
                        ".{8,}" +        // at least 8 character
                        "$"
                if (!password.matches(checkPass.toRegex())){
                    setErrors(binding.passEditText,"Enter Strong pasword")
                }

                val confirmPass = binding.confirmEditText.text.toString()
                if(confirmPass != password){
                    setErrors(binding.confirmEditText,"Password Must Be Same")
                }

            }
        }
    }



}

