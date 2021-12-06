package com.example.kipmnotes.activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.EditText
import android.widget.Toast
import com.example.kipmnotes.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope

class RegisterActivity : AppCompatActivity() {

    lateinit var binding:ActivityRegisterBinding
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        get instance of firebase
        auth  = FirebaseAuth.getInstance()


        // Design Login text
        changeColorTextView()


//        AgainLoginpage Call
        binding.txtAgainLogin.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

//        Add ClickListerner on Register Button
        binding.btnRegister.setOnClickListener {
            firebaseRegister()

        }

    }


//    get data and create account using firebase
    private fun firebaseRegister(){

    val fullName = binding.etRegisterName.text.toString()
    val collegeName = binding.etRegisterCollege.text.toString()
    val courseName = binding.etRegisterCourse.text.toString()
    val email = binding.etRegisterEmail.text.toString()
    val pass = binding.etRegisterPass.text.toString()
    val confirmPass = binding.etRegisterConfirmPass.text.toString()

    if (fullName.isEmpty()){
        setErrors(binding.etRegisterName,"Enter Full Name")
    }
    if (collegeName.isEmpty()){
        setErrors(binding.etRegisterCollege,"Please Enter College Name")
    }
    if (courseName.isEmpty()){
        setErrors(binding.etRegisterCourse,"Please Enter Your Course")
    }
    if (email.isEmpty()){
        setErrors(binding.etRegisterEmail,"Please Enter Email")
    }
    if (pass.isEmpty()){
        setErrors(binding.etRegisterPass,"Please Enter Password")
    }
    if (pass != confirmPass){
        setErrors(binding.etRegisterConfirmPass,"Password Do Not Match..!")
    }


    try {
        auth.createUserWithEmailAndPassword(email,pass).addOnSuccessListener {

            val intent  = Intent(this,HomeActivity::class.java)
            startActivity(intent)
            finish()

        }.addOnFailureListener {
            Toast.makeText(this,it.message, Toast.LENGTH_LONG).show()
        }
    }catch (e:Exception){
        Toast.makeText(this,"Please fill entries",Toast.LENGTH_LONG).show()
        }


    }


//    used for show errors on edittext when empty
    private fun setErrors(view:EditText,error:String){
        view.setError(error)
    }



    // Change color for designing purpose
    private fun changeColorTextView(){

        val mText = binding.txtAgainLogin.text.toString()

        val mSpannableString = SpannableString(mText)
        val mRed = ForegroundColorSpan(Color.RED)

        mSpannableString.setSpan(mRed,23,30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.txtAgainLogin.text = mSpannableString

    }



}

