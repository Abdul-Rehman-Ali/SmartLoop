package com.example.smartloop.admin

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.smartloop.databinding.ActivityDashboardAdminBinding

class DashboardAdmin : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDashboardAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ensure the button ID matches the one in your XML layout file
        binding.btnAddCategory.setOnClickListener {
            val intent = Intent(this, CategoryAddActivity::class.java)
            startActivity(intent)
        }
    }
}
