package com.example.smartloop

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.smartloop.databinding.ActivityLoginBinding
import com.example.smartloop.databinding.ActivityPasswordChangedBinding

class PasswordChanged : AppCompatActivity() {
    private lateinit var binding: ActivityPasswordChangedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasswordChangedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBackToLogin.setOnClickListener {
            val i = Intent(this, Login::class.java)
            finish()
            startActivity(i)
        }

    }
}