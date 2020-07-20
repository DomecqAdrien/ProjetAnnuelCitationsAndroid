package com.example.wrcsearchfilter.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.wrcsearchfilter.CitationResultActivity
import com.example.wrcsearchfilter.R
import com.example.wrcsearchfilter.model.Citation

class CitationConnexesAdapter(context: Context) : RecyclerView.Adapter<CitationConnexesViewHolder>() {
    val context = context
    var citations: List<Citation> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitationConnexesViewHolder {
        return CitationConnexesViewHolder(parent.inflate(R.layout.layout_books))
    }

    override fun getItemCount() = citations.size

    override fun onBindViewHolder(holder: CitationConnexesViewHolder, position: Int) {
        val recherche = citations[position]
        holder.itemView.setOnClickListener {
            val intent = Intent(context, CitationResultActivity::class.java)
            intent.putExtra("recherche", recherche.citation)
            context.startActivity(intent)
            //Toast.makeText(context, "click on " + productlist.get(position).getName(), Toast.LENGTH_LONG).show()
        }
        holder.bindData(recherche)
    }
}

class CitationConnexesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var bookTv: TextView = itemView.findViewById(R.id.citation_book)
    private var citationTv: TextView = itemView.findViewById(R.id.citation_texte)
    private var categoriesTv: TextView = itemView.findViewById(R.id.citation_categories)

    fun bindData(citation: Citation) {
        bookTv.text = citation.book.titre
        citationTv.text = citation.citation
        categoriesTv.text = citation.tags.toString()
    }
}

fun ViewGroup.inflate(@LayoutRes layout: Int): View {
    return LayoutInflater.from(context).inflate(layout, this, false)
}