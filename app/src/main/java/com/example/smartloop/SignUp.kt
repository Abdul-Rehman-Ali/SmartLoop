package com.example.smartloop

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.smartloop.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUp : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        binding.btnSignUp.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if(checkAllField()){
                auth.createUserWithEmailAndPassword(email,password).addOnCanceledListener {

                  Toast.makeText(this,"Successfully Account Created",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun checkAllField(): Boolean {
        val email = binding.etEmail.text.toString()
        if(binding.etUsername.text.toString() == ""){
            binding.etUsername.error = "This is required field"
            return false
        }
        if(binding.etEmail.text.toString() == ""){
            binding.etEmail.error = "This is required field"
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.etEmail.error = "Check email format"
            return false
        }
        if (binding.etPhone.text.toString() == ""){
            binding.etPhone.error = "This is required field"
            return false
        }
        if (binding.etPassword.text.toString() == ""){
            binding.etPassword.error = "This is required field"
            return false
        }
        if (binding.etPassword.length() < 8){
            binding.etPassword.error = "Password should be 8  characters"
            return false
        }
        if (binding.etConfirmPassword.text.toString() == ""){
            binding.etConfirmPassword.error = "This is required field"
            return false
        }
        if (binding.etPassword.text.toString() != binding.etConfirmPassword.text.toString()){
            binding.etPassword.error = "Password do not match"
            return false
        }

        return true
    }
}