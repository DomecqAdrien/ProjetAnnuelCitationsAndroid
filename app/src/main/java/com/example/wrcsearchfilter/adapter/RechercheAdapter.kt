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
        date.text = recherche.date.toString()
    }
}



/*class RechercheAdapter2(private var productlist: MutableList<Recherche>, val context: Context) : RecyclerView.Adapter<HolderView2>() {
    fun getProductlist(): List<Recherche> {
        return productlist
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderView2 {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.customitem, parent, false)
        return HolderView2(layout)
    }

    override fun onBindViewHolder(holder: HolderView2, position: Int) {
        var date: TextView = itemView.findViewById(R.id.date)
        var citation: TextView = itemView.findViewById(R.id.citation)
        citation.text = recherche.citation
        date.text = recherche.date.toString()
        holder.V_text.text = productlist[position].name
        holder.V_image.setImageResource(productlist[position].photo)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ShowCitationActivity::class.java)
            //intent.putExtra("Nom",productlist.get(position).getName());
            //intent.putExtra("Nom",productlist.get(position).getPhoto());
            context.startActivity(intent)
            Toast.makeText(context, "click on " + productlist[position].name, Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return productlist.size
    }

    fun setfilter(listitem: List<Item>?) {
        productlist = ArrayList()
        productlist.addAll(listitem!!)
        notifyDataSetChanged()
    }

     class HolderView2(itemview: View) : RecyclerView.ViewHolder(itemview) {
        private var date: TextView = itemView.findViewById(R.id.date)
        private var citation: TextView = itemView.findViewById(R.id.citation)
    }

}*/