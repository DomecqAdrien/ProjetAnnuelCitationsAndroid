package com.example.citationAppliMobile.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.citationAppliMobile.CitationResultActivity
import com.example.citationAppliMobile.R
import com.example.citationAppliMobile.model.Citation

class CitationsAdapter(private val context: Context) : RecyclerView.Adapter<CitationsViewHolder>() {
    var citations: List<Citation> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitationsViewHolder {
        return CitationsViewHolder(parent.inflate(R.layout.layout_result))
    }

    override fun getItemCount() = citations.size

    override fun onBindViewHolder(holder: CitationsViewHolder, position: Int) {
        val citation = citations[position]
        holder.itemView.setOnClickListener {
            val intent = Intent(context, CitationResultActivity::class.java)
            intent.putExtra("recherche",citation.citation)
            context.startActivity(intent)
        }
        holder.bindData(citation)
    }
}

class CitationsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var citationTv: TextView = itemView.findViewById(R.id.citation_text)

    fun bindData(citation: Citation) {
        citationTv.text = citation.citation
    }
}