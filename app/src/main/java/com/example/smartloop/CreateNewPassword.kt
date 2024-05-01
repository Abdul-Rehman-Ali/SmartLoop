package com.example.smartloop

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.smartloop.databinding.ActivityCreateNewPasswordBinding
import com.example.smartloop.databinding.ActivityForgetPasswordBinding

class CreateNewPassword : AppCompatActivity() {
    private lateinit var binding: ActivityCreateNewPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNewPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            val i = Intent(this, PasswordChanged::class.java)
            startActivity(i)
        }


    }
}