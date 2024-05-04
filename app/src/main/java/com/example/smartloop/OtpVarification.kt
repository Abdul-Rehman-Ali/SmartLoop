package com.example.smartloop

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.smartloop.databinding.ActivityLoginBinding
import com.example.smartloop.databinding.ActivityOtpVarificationBinding

class OtpVarification : AppCompatActivity() {
    private lateinit var binding: ActivityOtpVarificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpVarificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVerify.setOnClickListener {
            val i = Intent(this, CreateNewPassword::class.java)
            finish()
            startActivity(i)
        }

    }
}