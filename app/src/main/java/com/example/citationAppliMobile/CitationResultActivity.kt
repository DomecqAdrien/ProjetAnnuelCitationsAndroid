package com.example.citationAppliMobile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.citationAppliMobile.JsonManager.loadJSON
import com.example.citationAppliMobile.JsonManager.writeJSON
import com.example.citationAppliMobile.adapter.CitationConnexesAdapter
import com.example.citationAppliMobile.model.APIParserDTO
import com.example.citationAppliMobile.model.Citation
import com.example.citationAppliMobile.service.CitationService
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class CitationResultActivity : AppCompatActivity() {
    private var citation: Citation? = null
    lateinit var author: TextView
    private lateinit var citationConnexesRv: RecyclerView
    private lateinit var citationConnexesAdapter: CitationConnexesAdapter
    private var citationConnexes: MutableList<Citation> = ArrayList()
    private lateinit var citationService: CitationService
    private lateinit var waitMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.waiting_screen)

        waitMessage = findViewById(R.id.waiting_message)

        val recherche = this.intent.getStringExtra("recherche").toString()
        Log.i("recherche", recherche)
        val retrofit = Retrofit.Builder()
                .baseUrl(getString(R.string.server))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        citationService = retrofit.create(CitationService::class.java)

        citationService.getCitation(recherche).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {

                var status = ""
                val queueName = response.body().toString()
                Log.i("queue",queueName)
                val callResponse = citationService.getQueueResponse(queueName);

                getResponse(queueName)

            }

            override fun onFailure(call: Call<String?>, throwable: Throwable) {
                waitMessage.text = "Erreur"
                Log.i("error:", throwable.message)
            }


        })
    }

    fun getResponse(queueName: String) {
        citationService.getQueueResponse(queueName).enqueue(object: Callback<APIParserDTO>{
            override fun onFailure(call: Call<APIParserDTO>, t: Throwable) {
                Log.i("error",t.message)
                waitMessage.text = "Erreur"
            }

            override fun onResponse(call: Call<APIParserDTO>, response: Response<APIParserDTO>) {
                val status = response.body()?.statut.toString()
                Log.i("status",status)
                if(status == "OK"){
                    citation = response.body()?.citation
                    setContent()
                }
                else{
                    if(status != "Pas de nouveau message"){
                        waitMessage.text = status
                    }
                    Thread.sleep(1000)
                    getResponse(queueName)
                }
            }
        })
    }

    private fun setContent() {
        Log.i("citation", citation.toString())
        setContentView(R.layout.activity_citation_result)
        author = findViewById(R.id.book_author)
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