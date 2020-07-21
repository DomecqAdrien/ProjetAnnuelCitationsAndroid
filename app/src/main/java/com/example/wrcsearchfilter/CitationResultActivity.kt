package com.example.wrcsearchfilter

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wrcsearchfilter.JsonManager.loadJSON
import com.example.wrcsearchfilter.JsonManager.writeJSON
import com.example.wrcsearchfilter.adapter.CitationConnexesAdapter
import com.example.wrcsearchfilter.adapter.RechercheAdapter
import com.example.wrcsearchfilter.model.Citation
import com.example.wrcsearchfilter.model.Recherche
import com.example.wrcsearchfilter.retrofit.JsonPlaceHolderApi
import org.json.JSONException
import org.json.JSONObject
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class CitationResultActivity : AppCompatActivity() {
    private var citation: Citation? = null
    lateinit var author: TextView
    private lateinit var citationConnexesRv: RecyclerView
    private lateinit var citationConnexesAdapter: CitationConnexesAdapter
    private var citationConnexes: MutableList<Citation> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.waiting_screen)

        val recherche = this.intent.getStringExtra("recherche")
        Log.i("recherche", recherche)
        val retrofit = Retrofit.Builder()
                .baseUrl("https://3fa1351831d9.eu.ngrok.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val jsonPlaceHolderApiI = retrofit.create(JsonPlaceHolderApi::class.java)
        val call = jsonPlaceHolderApiI.getCitation(recherche)


        call.enqueue(object : Callback<Citation?> {
            override fun onResponse(call: Call<Citation?>, response: Response<Citation?>) {
                setContentView(R.layout.activity_citation_result)
                author = findViewById(R.id.book_author)
                if (!response.isSuccessful) {
                    Log.i("response", "fail")
                    author.text = "Code: " + response.code()
                    return
                }
                Log.i("body", response.toString())
                citation = response.body()
                Log.i("citation", citation.toString())
                author.text = citation!!.book.auteur.prenom + ' ' + citation!!.book.auteur.nom

                val title = findViewById<TextView>(R.id.book_title)
                title.text = citation!!.book.titre

                val anneeParutionLivre = findViewById<TextView>(R.id.book_annee_parution)
                anneeParutionLivre.text = citation!!.book.anneeParution.toString()

                val citationTv = findViewById<TextView>(R.id.text_citation)
                citationTv.text = citationTv.text.toString() + citation!!.citation

                val bookImg = findViewById<ImageView>(R.id.book_img)
                Glide.with(bookImg).load(citation!!.book.imageUrl).into(bookImg)

                val tagsTv = findViewById<TextView>(R.id.citation_tags)
                tagsTv.text = tagsTv.text.toString() + ' ' + citation!!.tags

                citationConnexes = citation!!.citationsConnexes
                citationConnexesRv = findViewById<View>(R.id.list_citation_connexes) as RecyclerView
                citationConnexesAdapter = CitationConnexesAdapter(this@CitationResultActivity)
                citationConnexesRv.apply {
                    adapter = citationConnexesAdapter
                }
                citationConnexesAdapter.citations = citationConnexes




                /*if(citation.getCitationsConnexes() != null){
                    for (Citation citationConnexe : citation.getCitationsConnexes()) {

                        String content = ""
                                + "Livre: " + citationConnexe.getBook().getTitre() + "\n"
                                + "Citation: " + citationConnexe.getCitation() + "\n"
                                + "Catégorie(s) similaire(s): " + citationConnexe.getTags() + "\n\n";

                        textViewResult.append(content);
                    }
                }*/
            }

            override fun onFailure(call: Call<Citation?>, throwable: Throwable) {
                setContentView(R.layout.layout_citation_not_found)
                Log.i("error:", throwable.message)
                //author!!.text = "Aucune citation trouvée."
            }


        })
    }

    public fun back(v: View?){
        onBackPressed()
    }

    fun showOtherBooks(v: View?) {
        val intent = Intent(this, AuthorBooksActivity::class.java)
        intent.putExtra("auteur", citation!!.book.auteur)
        this.startActivity(intent)
    }

    @Throws(JSONException::class)
    fun addToFavorites(v: View?) {
        val obj = JSONObject(loadJSON(this))
        val arr = obj.getJSONArray("favoris")
        val newFav = JSONObject()
        val date = Calendar.getInstance().time
        val d: DateFormat = SimpleDateFormat("dd-MM-yyyy hh:mm:ss")
        var favExists = false
        for (i in 0 until arr.length()) {
            val c = arr.getJSONObject(i).getString("citation")
            if (c == citation!!.citation) favExists = true
        }
        if (!favExists) {
            newFav.put("citation", citation!!.citation).put("date", d.format(date))
            arr.put(newFav)
            writeJSON(this, obj.toString())
            Toast.makeText(this, "Ajouté au favoris", Toast.LENGTH_LONG).show()
        } else Toast.makeText(this, "La citation existe déjà dans vos favoris", Toast.LENGTH_LONG).show()
    }
}