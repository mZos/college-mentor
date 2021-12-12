package com.example.kipmnotes.activity

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kipmnotes.databinding.ActivityGithubAuthBinding
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider


class GithubAuthActivity : AppCompatActivity() {

    lateinit var binding : ActivityGithubAuthBinding
    lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGithubAuthBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()

        binding.btnGithubLogin.setOnClickListener{
            val email = binding.etGithubEmail.text.toString()

            if (email.isEmpty()){
                setErrors(binding.etGithubEmail, "Please enter your email")
            }
            else{
                val provider = OAuthProvider.newBuilder("github.com")

                provider.addCustomParameter("login", email)

                val scopes: ArrayList<String?> = object : ArrayList<String?>() {
                    init {
                        add("user:email")
                    }
                }
                provider.scopes = scopes

                val pendingResultTask: Task<AuthResult> = auth.getPendingAuthResult() as Task<AuthResult>
                if (pendingResultTask != null) {
                    pendingResultTask
                        .addOnSuccessListener(
                            OnSuccessListener<AuthResult?> {

                            })
                        .addOnFailureListener(
                            OnFailureListener {
                                Toast.makeText(this,it.message,Toast.LENGTH_LONG).show()
                            })
                } else {
                    auth
                        .startActivityForSignInWithProvider( /* activity= */this, provider.build())
                        .addOnSuccessListener(
                            OnSuccessListener<AuthResult?> {
                                openNextActivity()
                            })
                        .addOnFailureListener(
                            OnFailureListener {
                                Toast.makeText(this,it.message,Toast.LENGTH_LONG).show()
                            })
                }
            }
        }
    }

//  Open New Activity
    fun openNextActivity(){
        val intent = Intent(this,HomeActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or  Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }


    //  used for show errors on edittext when empty
    private fun setErrors(view: EditText, error: String) {
        view.error = error
    }
}