package com.example.smartloop.admin

import android.app.AlertDialog
import android.app.Application
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.smartloop.R
import com.example.smartloop.databinding.ActivityPdfAddBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class PdfAddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPdfAddBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog
    private lateinit var categoreyArrayList: ArrayList<ModelCategorey>

    private var pdfUri : Uri? = null
    private var TAG = "PDF_ADD_TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityPdfAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        loadpdfcategories()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setCanceledOnTouchOutside(false)


        binding.tvCategory.setOnClickListener {
            categoryPickDialog()
        }

        binding.attach.setOnClickListener {
            pdfPickIntent()
        }

        binding.btnSubmit.setOnClickListener {
            validateData()
        }
    }

    private var title = ""
    private var description = ""
    private var categorey = ""
    private fun validateData(){
        Log.d(TAG, "validateData : validate data")
        title = binding.etTitle.text.toString().trim()
        description = binding.etDescription.text.toString().trim()
        categorey = binding.tvCategory.text.toString().trim()

        if (title.isEmpty()){
            Toast.makeText(this, "Enter Title",Toast.LENGTH_SHORT).show()
        }else if (description.isEmpty()){
            Toast.makeText(this, "Enter Description",Toast.LENGTH_SHORT).show()
        } else if (categorey.isEmpty()){
            Toast.makeText(this, "Enter Categorey",Toast.LENGTH_SHORT).show()
        } else if (pdfUri == null){
            Toast.makeText(this, "Pick PDF",Toast.LENGTH_SHORT).show()
        }
        else{
            uploadPdfToStorage()
        }

    }

    private fun uploadPdfToStorage(){
        Log.d(TAG, "uploadPdfToStorage : uploading to storage...")
        progressDialog.setTitle("Uploading PDF...")
        progressDialog.show()

        val timestamp = System.currentTimeMillis()
        val filePathAndName = "Book/$timestamp"

        val storageReference = FirebaseStorage.getInstance().getReference(filePathAndName)
        storageReference.putFile(pdfUri!!)
            .addOnSuccessListener {taskSnapshot ->
                Log.d(TAG, "uploadPdfToStorage : PDF uploading now getting url...")

                val uriTask : Task<Uri> = taskSnapshot.storage.downloadUrl
                while (!uriTask.isSuccessful);
                val uploadPdfUrl = "${uriTask.result}"


                uploadPdfIntoDb (uploadPdfUrl, timestamp)

            }
            .addOnFailureListener {e ->
                Log.d(TAG, "uploadPdfToStorage : failed to upload due to ${e.message}")
                progressDialog.dismiss()
                Toast.makeText(this, "Failed to upload due to ${e.message}",Toast.LENGTH_SHORT).show()
            }
    }


    private fun uploadPdfIntoDb(uploadPdfUrl : String, timestamp: Long){
        Log.d(TAG, "uploadPdfToDb : uploading to database...")
        progressDialog.setMessage("Uploading PDF Into...")

        val uid = firebaseAuth.uid
        val hashMap: HashMap<String, Any> = HashMap()
        hashMap["uid"] = "$uid"
        hashMap["title"] = "$title"
        hashMap["description"] = "$description"
        hashMap["categoreyId"] = "$selectedCategoreyId"
        hashMap["url"] = "$uploadPdfUrl"
        hashMap["timestamp"] = "$timestamp"
        hashMap["viewCount"] = 0
        hashMap["downloadCount"] = 0

        val ref = FirebaseDatabase.getInstance().getReference("Books")
        ref.child("$timestamp")
            .setValue(hashMap)
            .addOnCanceledListener {
                Log.d(TAG,"uploadPathIntoDb: Uploading to db")
                progressDialog.dismiss()
                Toast.makeText(this, "Uploaded...",Toast.LENGTH_SHORT).show()
                pdfUri = null
            }
            .addOnFailureListener { e ->
                Log.d(TAG, "uploadPathIntoDb : failed to upload due to ${e.message}")
                progressDialog.dismiss()
                Toast.makeText(this, "Failed to upload due to ${e.message}",Toast.LENGTH_SHORT).show()
            }
    }
    private fun loadpdfcategories(){
        Log.d(TAG, "loadpdfcategories: Loading Pdf  Categories")
        categoreyArrayList = ArrayList()
        val ref = FirebaseDatabase.getInstance().getReference("Category")
        ref.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                categoreyArrayList.clear()
                for(ds in snapshot.children){
                    val model = ds.getValue(ModelCategorey::class.java)
                    categoreyArrayList.add(model!!)
                    Log.d(TAG, "onDataChanged: ${model.category}")
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    private var selectedCategoreyId = ""
    private var selectedCategorytitle = ""
    private fun categoryPickDialog(){
        Log.d(TAG, "categoryPickDialog: Showing Pdf Category Pick Dialog")
        val categoryArray = arrayOfNulls<String>(categoreyArrayList.size)
        for(i in categoreyArrayList.indices){
            categoryArray[i] = categoreyArrayList[i].category
        }
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Pick Categorey")
            .setItems(categoryArray){ dialog, which ->
                selectedCategorytitle = categoreyArrayList[which].category
                selectedCategoreyId = categoreyArrayList[which].id

                binding.tvCategory.text = selectedCategorytitle
                Log.d(TAG, "categoreyPickDialog: selected categorey id : $selectedCategoreyId")
                Log.d(TAG, "categoreyPickDialog: selected categorey id : $selectedCategorytitle")
            }
            .show()
    }

    private fun pdfPickIntent(){
        Log.d(TAG, "pdfPickIntent : starting pdf pick intent")
        val intent = Intent()
        intent.type = "application/pdf"
        intent.action=  Intent.ACTION_GET_CONTENT
        pdfAddActivityResultLauncher.launch(intent)
    }

    val pdfAddActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback<ActivityResult> { result ->
            if (result.resultCode == RESULT_OK){
                Log.d(TAG, "PDF Picked ")
                pdfUri = result.data!!.data

            } else {
                Log.d(TAG, "PDF Picked Cancelled")
                Toast.makeText(this, "Cancelled",Toast.LENGTH_SHORT).show()
            }
        }
    )
}


