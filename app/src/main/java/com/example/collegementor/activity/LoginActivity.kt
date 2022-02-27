package com.example.collegementor.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.collegementor.R
import com.example.collegementor.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val REQUEST_CODE_SIGN_IN = 0
class LoginActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityLoginBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //connect to app
        firebaseAuth = Firebase.auth

        //Design Register text
        changeColorTextView()

        binding.btnLogin.setOnClickListener(this)
        binding.googleLoginButton.setOnClickListener(this)
        binding.txtRegister.setOnClickListener(this)
        binding.githubLoginButton.setOnClickListener(this)

        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    googleAuthFirFirebase(account.idToken!!)
                } catch (e: ApiException) {
                    Log.e("LoginActivity", e.message.toString())
                }
            }
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.btnLogin -> {
                loginUser()
            }
            R.id.googleLoginButton -> {
                googleSignIn()
            }
            R.id.txtRegister -> {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.githubLoginButton -> {
                val intent = Intent(this, GithubAuthActivity::class.java)
                startActivity(intent)
            }
        }
    }

    //  change color for designing purpose
    private fun changeColorTextView() {
        val mText = binding.txtRegister.text.toString()

        val mSpannableString = SpannableString(mText)
        val mRed = ForegroundColorSpan(Color.RED)

        mSpannableString.setSpan(mRed, 23, 35, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.txtRegister.text = mSpannableString
    }

    // Google Authentication
    private fun googleSignIn() {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()

        // get popup window of google sign in
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        //  intent through google signIn button
        googleSignInClient.signInIntent.also {
            resultLauncher.launch(it)
        }
    }

    //get credentials and perform authentications with Google
    private fun googleAuthFirFirebase(idToken: String) {
        val credentials = GoogleAuthProvider.getCredential(idToken, null)
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                firebaseAuth.signInWithCredential(credentials)
                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@LoginActivity, e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    override fun onStart() {
        super.onStart()
        val currentUser = firebaseAuth.currentUser
        currentUser?.let {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    //Login registered user using email and password
    private fun loginUser() {
        val email = binding.etLoginEmail.text.toString().trim()
        val pass = binding.etLoginPass.text.toString()

        if (email.isEmpty()) {
            setErrors(binding.etLoginEmail, "Please enter your email")
        }
        if (pass.isEmpty()) {
            setErrors(binding.etLoginPass, "Please enter correct password")
        }

        try {
            firebaseAuth.signInWithEmailAndPassword(email, pass).addOnSuccessListener {
                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this@LoginActivity, e.message, Toast.LENGTH_LONG).show()
        }
    }

    //  used show errors on edittext when empty
    private fun setErrors(view: EditText, error: String) {
        view.error = error
    }
}