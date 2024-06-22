package com.example.smartloop

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.smartloop.admin.MyApplication
import com.example.smartloop.databinding.ActivityPdfDetailBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PdfDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPdfDetailBinding
    private var bookId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize view binding
        binding = ActivityPdfDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bookId = intent.getStringExtra("bookId")!!

        MyApplication.incrementBookViewCount(bookId)

        loadBookDetail()

        binding.btnReadBook.setOnClickListener {
           val  intent = Intent(this, PdfViewActivity::class.java)
            intent.putExtra("bookId", bookId)
            startActivity(intent)
        }
    }

    private fun loadBookDetail() {
        val ref = FirebaseDatabase.getInstance().getReference("Book")
        ref.child(bookId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val categoryId = snapshot.child("categoryId").getValue(String::class.java) ?: ""
                    val description = snapshot.child("description").getValue(String::class.java) ?: ""
                    val downloadCount = snapshot.child("downloadCount").getValue(Long::class.java) ?: 0L
                    val timestamp = snapshot.child("timestamp").getValue(Long::class.java) ?: 0L
                    val title = snapshot.child("title").getValue(String::class.java) ?: ""
                    val uid = snapshot.child("uid").getValue(String::class.java) ?: ""
                    val url = snapshot.child("url").getValue(String::class.java) ?: ""
                    val viewCount = snapshot.child("viewCount").getValue(Long::class.java) ?: 0L

                    val date = MyApplication.formatTimeStamp(timestamp)

                    MyApplication.loadCategory(categoryId, binding.tvCategory)
                    MyApplication.loadPdfFromUrlSinglePage(url, title, binding.pdfview, binding.progressBar, binding.tvPages)
                    MyApplication.loadPdfSize(url, title, binding.tvSize)

                    binding.tvTitle.text = title
                    binding.tvDescription.text = description
                    binding.tvViews.text = viewCount.toString()
                    binding.tvDownloads.text = downloadCount.toString()
                    binding.tvDate.text = date
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle database error
                }
            })
    }
}
