package com.example.smartloop

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.smartloop.databinding.ActivityLoginBinding
import com.example.smartloop.databinding.ActivityMainBinding

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.forgetPassword.setOnClickListener {
            val i = Intent(this, ForgetPassword::class.java)
            startActivity(i)
            finish()
        }

        binding.btnLogin.setOnClickListener {
            val i = Intent(this, Home::class.java)
            startActivity(i)
            finish()
        }

    }
}