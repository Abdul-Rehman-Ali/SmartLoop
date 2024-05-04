package com.example.smartloop

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.smartloop.databinding.ActivityForgetPasswordBinding
import com.example.smartloop.databinding.ActivityLoginBinding

class ForgetPassword : AppCompatActivity() {
    private lateinit var binding: ActivityForgetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSendCode.setOnClickListener {
            val i = Intent(this, OtpVarification::class.java)
            finish()
            startActivity(i)
        }

    }
}