package com.example.wrcsearchfilter

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.wrcsearchfilter.adapter.BooksConnexesAdapter
import com.example.wrcsearchfilter.model.Auteur
import com.example.wrcsearchfilter.model.Book
import kotlinx.android.synthetic.main.activity_author_books.*
import java.util.ArrayList

class AuthorBooksActivity : AppCompatActivity() {

    private lateinit var bookRv: RecyclerView
    private lateinit var bookAdapter: BooksConnexesAdapter
    private var books: MutableList<Book> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_author_books)

        val auteur = intent.getSerializableExtra("auteur") as? Auteur

        books = auteur!!.books

        books = books.sortedWith(compareBy {it.anneeParution}) as MutableList<Book>
        bookRv = findViewById<View>(R.id.list_books) as RecyclerView
        bookAdapter = BooksConnexesAdapter(this)
        bookRv.apply {
            adapter = bookAdapter
        }
        bookAdapter.books = books

        author_name.text = auteur.prenom + ' ' + auteur.nom
    }
}