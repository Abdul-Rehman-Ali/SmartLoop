package com.example.smartloop.admin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.smartloop.databinding.RowPdfAdminBinding

class AdapterPdfAdmin:RecyclerView.Adapter<AdapterPdfAdmin.HolderPdfAdmin>, Filterable{


    private var context : Context
    public  var pdfArrayList : ArrayList<ModelPdf>
    private val filterList : ArrayList<ModelPdf>
    private lateinit var binding: RowPdfAdminBinding


    var filter : FilterPdfAdmin? = null
    constructor(context: Context, pdfArrayList: ArrayList<ModelPdf>) : super() {
        this.context = context
        this.pdfArrayList = pdfArrayList
        this.filterList = pdfArrayList
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderPdfAdmin {
        binding = RowPdfAdminBinding.inflate(LayoutInflater.from(context), parent, false)
        return HolderPdfAdmin(binding.root)
    }

    override fun onBindViewHolder(holder: HolderPdfAdmin, position: Int) {
        val model = pdfArrayList[position]
        val pdfId = model.id
        val categoryId = model.categoryId
        val title = model.title
        val description = model.description
        val pdfUrl = model.url
        val timestamp = model.timestamp

        val formattedDate = MyApplication.formatTimeStamp(timestamp)
        holder.tvtitle.text = title
        holder.tvdescription.text = description
        holder.tvdate.text = formattedDate


        MyApplication.loadCategory(categoryId, holder.tvcategory)
        MyApplication.loadPdfFromUrlSinglePage(pdfUrl, title, holder.pdfviewer, holder.progressBar, null)
        MyApplication.loadPdfSize(pdfUrl,title,holder.tvsize)
    }

    override fun getItemCount(): Int {
        return  pdfArrayList.size
    }


    override fun getFilter(): Filter {
        if (filter == null){
            filter = FilterPdfAdmin(filterList, this)
        }
        return filter as FilterPdfAdmin
    }

    inner class HolderPdfAdmin (itemView: View) : RecyclerView.ViewHolder(itemView){
        val pdfviewer = binding.pdfview
        val progressBar = binding.progressbar
        val tvtitle = binding.tvTitle
        val tvdescription = binding.tvDescription
        val tvcategory = binding.tvCategory
        val tvsize = binding.tvSize
        val tvdate = binding.tvDate
        val btnmore = binding.btnMore

    }

}