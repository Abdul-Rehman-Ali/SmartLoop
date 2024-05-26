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

        binding.btnAddCategory.setOnClickListener {
            val i = Intent(this, CategoryAddActivity::class.java)
            startActivity(i)
        }
    }
}
