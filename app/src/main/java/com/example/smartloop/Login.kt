package com.example.smartloop

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.smartloop.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        binding.forgetPassword.setOnClickListener {
            val i = Intent(this, ForgetPassword::class.java)
            startActivity(i)
            finish()
        }

        binding.btnLogin.setOnClickListener {

            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

//            val i = Intent(this, Home::class.java)
//            startActivity(i)
//            finish()

            if (checkAllField()) {
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {task ->
                    if (task.isSuccessful) {

                        Toast.makeText(this, "Successfully Sign In",
                            Toast.LENGTH_SHORT).show()
                        clearFields()

                        val i = Intent(this, Home::class.java)
                        startActivity(i)
                        finish()
                    }
                    else {
                        Toast.makeText(baseContext, "Email or password wrong",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    }

    private fun checkAllField(): Boolean {
        val email = binding.etEmail.text.toString()
        if(binding.etEmail.text.toString() == ""){
            binding.etEmail.error = "This is required field"
            binding.etEmail.requestFocus()
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.etEmail.error = "Check email format"
            binding.etEmail.requestFocus()
            return false
        }
        if (binding.etPassword.text.toString() == ""){
            binding.etPassword.error = "This is required field"
            binding.etPassword.requestFocus()
            return false
        }
        if (binding.etPassword.length() < 8){
            binding.etPassword.error = "Password should be 8  characters"
            binding.etPassword.requestFocus()
            return false
        }
        return true
    }

    private fun clearFields() {

        binding.etEmail.text.clear()
        binding.etPassword.text?.clear()

        // Clear any errors that might have been set
        binding.etEmail.error = null
        binding.etPassword.error = null
    }

//    @Deprecated("Deprecated in Java")
//    override fun onBackPressed() {
//
//        finishAffinity()
//        super.onBackPressed()
//
//
//    }

}