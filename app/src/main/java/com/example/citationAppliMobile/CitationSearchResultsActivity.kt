package com.example.citationAppliMobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.citationAppliMobile.adapter.CitationsAdapter
import com.example.citationAppliMobile.model.APIParserDTO
import com.example.citationAppliMobile.model.Citation
import com.example.citationAppliMobile.service.CitationService
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class CitationSearchResultsActivity : AppCompatActivity() {

    private var citations: List<Citation> = listOf()

    private lateinit var citationsRecyclerView: RecyclerView
    private lateinit var citationsAdapter : CitationsAdapter
    private lateinit var citationService: CitationService
    private lateinit var waitMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.waiting_screen)

        waitMessage = findViewById(R.id.waiting_message)

        citationsAdapter = CitationsAdapter(this)


        val recherche = this.intent.getStringExtra("recherche")
        val client = Retrofit.Builder()
                .baseUrl(getString(R.string.server))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        citationService = client.create(CitationService::class.java)


        citationService.searchCitationsByText(recherche).enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i("error:", t.message)
                waitMessage.text = "Erreur"
            }

            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                val queueName = response.body().toString()
                Log.i("queue",queueName)
                getResponse(queueName)
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
                    setContentView(R.layout.activity_citation_search_results)

                    citationsRecyclerView = findViewById(R.id.citations_recycler_view)
                    citationsRecyclerView.apply {
                        citationsAdapter.citations = citations
                        adapter = citationsAdapter

                    }
                    Log.i("count",response.body()?.citations?.count().toString())
                    citations = response.body()?.citations?: emptyList()
                    citationsAdapter.citations = citations
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

}