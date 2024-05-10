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

//        binding.etPassword.setCompoundDrawablesWithIntrinsicBounds(
//            0,   // left drawable, if any (0 if none)
//            0,   // top drawable, if any (0 if none)
//            R.drawable.new_icon, // right drawable (end)
//            0    // bottom drawable, if any (0 if none)
//        )

        auth = Firebase.auth
        binding.btnSignUp.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (checkAllField()) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {

                            Toast.makeText(this, "Successfully Account Created",
                                Toast.LENGTH_SHORT).show()
                            clearFields()
                        } else {
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }

    private fun checkAllField(): Boolean {
        val email = binding.etEmail.text.toString()
        if(binding.etUsername.text.toString() == ""){
            binding.etUsername.error = "This is required field"
            binding.etUsername.requestFocus()
            return false
        }
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
        if (binding.etPhone.text.toString() == ""){
            binding.etPhone.error = "This is required field"
            binding.etPhone.requestFocus()
            return false
        }
        if ((binding.etPhone.length() < 11) ){
            binding.etPhone.error = "Enter correct  number"
            binding.etPhone.requestFocus()
            return false
        }
        if ((binding.etPhone.length() > 11) ){
            binding.etPhone.error = "Enter correct  number"
            binding.etPhone.requestFocus()
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
        if (binding.etConfirmPassword.text.toString() == ""){
            binding.etConfirmPassword.error = "This is required field"
            binding.etConfirmPassword.requestFocus()
            return false
        }
        if (binding.etPassword.text.toString() != binding.etConfirmPassword.text.toString()){
            binding.etConfirmPassword.error = "Password do not match"
            binding.etConfirmPassword.requestFocus()
            return false
        }

        return true
    }

    private fun clearFields() {
        binding.etUsername.text.clear()
        binding.etEmail.text.clear()
        binding.etPhone.text.clear()
        binding.etPassword.text.clear()
        binding.etConfirmPassword.text.clear()

        // Clear any errors that might have been set
        binding.etUsername.error = null
        binding.etEmail.error = null
        binding.etPhone.error = null
        binding.etPassword.error = null
        binding.etConfirmPassword.error = null
    }

}