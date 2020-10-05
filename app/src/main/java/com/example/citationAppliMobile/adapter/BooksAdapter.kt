package com.example.citationAppliMobile.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.citationAppliMobile.R
import com.example.citationAppliMobile.model.Book

class BooksConnexesAdapter(context: Context) : RecyclerView.Adapter<BooksViewHolder>() {
    val context = context
    var books: List<Book> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        return BooksViewHolder(parent.inflate(R.layout.layout_books))
    }

    override fun getItemCount() = books.size

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        val book = books[position]
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://amazon.fr/s?k="+book.titre)
            context.startActivity(intent)
            //Toast.makeText(context, "click on " + productlist.get(position).getName(), Toast.LENGTH_LONG).show()
        }
        holder.bindData(book)
    }
}

class BooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val context: Context = itemView.context
    private var bookIv: ImageView = itemView.findViewById(R.id.book_img)
    private var bookTitleTv: TextView = itemView.findViewById(R.id.book_title)

    fun bindData(book: Book) {
        Glide.with(context)
                .load(book.imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(bookIv)
        bookTitleTv.text = book.titre
    }
}

