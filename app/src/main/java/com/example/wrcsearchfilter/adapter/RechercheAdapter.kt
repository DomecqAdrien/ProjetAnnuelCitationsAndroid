package com.example.wrcsearchfilter.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.wrcsearchfilter.R
import com.example.wrcsearchfilter.CitationResultActivity
import com.example.wrcsearchfilter.model.Recherche

class RechercheAdapter(context: Context) : RecyclerView.Adapter<RechercheViewHolder>() {
    val context = context
    var recherches: List<Recherche> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RechercheViewHolder {
        return RechercheViewHolder(parent.inflate(R.layout.layout_historique))
    }

    override fun getItemCount() = recherches.size

    override fun onBindViewHolder(holder: RechercheViewHolder, position: Int) {
        val recherche = recherches[position]
        holder.itemView.setOnClickListener {
            val intent = Intent(context, CitationResultActivity::class.java)
            intent.putExtra("recherche", recherche.citation)
            context.startActivity(intent)
            //Toast.makeText(context, "click on " + productlist.get(position).getName(), Toast.LENGTH_LONG).show()
        }
        holder.bindData(recherche)
    }
}

class RechercheViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var date: TextView = itemView.findViewById(R.id.date)
    private var citation: TextView = itemView.findViewById(R.id.citation)

    fun bindData(recherche: Recherche) {
        citation.text = recherche.citation
        date.text = recherche.date
    }
}