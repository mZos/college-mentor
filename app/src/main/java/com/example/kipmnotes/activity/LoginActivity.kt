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
import com.example.kipmnotes.R
import com.example.kipmnotes.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import java.lang.Exception
import com.google.android.gms.auth.api.Auth

const val REQUEST_CODE_SIGN_IN = 0


class LoginActivity : AppCompatActivity() {

    lateinit var binding : ActivityLoginBinding
    private lateinit var googleSignInClient: GoogleSignInClient


//    Initialize Firebase in app
    private lateinit var auth:FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //connect to app
        auth  = Firebase.auth


//        Design Register text
        changeColorTextView()


//        Add click listeners on LoginButton
        binding.btnLogin.setOnClickListener {
            loginUser()
        }

        //Add click Listerners on GoogleSignInButton
        binding.googleLoginButton.setOnClickListener {
            googleSignINMethod()
        }

//        Add clicks Listeners op Register text
        binding.txtRegister.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


//  change color for designing purpose
    private fun changeColorTextView(){

        val mText = binding.txtRegister.text.toString()

        val mSpannableString = SpannableString(mText)
        val mRed = ForegroundColorSpan(Color.RED)

        mSpannableString.setSpan(mRed,23,35,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.txtRegister.text = mSpannableString

    }


//  Add Google Authentication
    private fun googleSignINMethod(){
        // Adding google sign in method in App
        val gso =  GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()


        // get popup window of google signin
        googleSignInClient = GoogleSignIn.getClient(this, gso)



    //  intent through google signIn button
        googleSignInClient.signInIntent.also {
            startActivityForResult(it, REQUEST_CODE_SIGN_IN)

        }

    }


//  override this function for get actual result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SIGN_IN){
            val account = GoogleSignIn.getSignedInAccountFromIntent(data).result
            account?.let {
                googleAuthFirFirebase(it)
            }
        }
    }


//    get credentials and perform authencations with Google
    private fun googleAuthFirFirebase(account:GoogleSignInAccount){
        val credentials = GoogleAuthProvider.getCredential(account.idToken,null)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                auth.signInWithCredential(credentials)
                val intent = Intent(this@LoginActivity,HomeActivity::class.java)
                startActivity(intent)
                finish()

            }catch (e:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@LoginActivity,e.message,Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


//  check login already or not
    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        currentUser?.let{
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


//  Create function for login user
    private fun loginUser() {

    val email = binding.etLoginEmail.text.toString()
    val pass = binding.etLoginPass.text.toString()

    if (email.isEmpty()) {
        setErrors(binding.etLoginEmail, "Please enter your email")
    }

    if (pass.isEmpty()) {
        setErrors(binding.etLoginPass, "Please enter correct password")
    }

    try {
        auth.signInWithEmailAndPassword(email, pass).addOnSuccessListener {

            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }.addOnFailureListener {
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
        }

    } catch (e: Exception) {
        Toast.makeText(this@LoginActivity, "Please Fill Credentials", Toast.LENGTH_LONG).show()
    }
}

//  used for show errors on edittext when empty
    private fun setErrors(view: EditText, error:String){
        view.setError(error)
    }

}