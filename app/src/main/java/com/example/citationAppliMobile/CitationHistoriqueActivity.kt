package com.example.citationAppliMobile

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.citationAppliMobile.JsonManager.loadJSON
import com.example.citationAppliMobile.adapter.RechercheAdapter
import com.example.citationAppliMobile.model.Recherche
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class CitationHistoriqueActivity : AppCompatActivity() {

    private lateinit var historiqueRv: RecyclerView
    private lateinit var historiqueAdapter: RechercheAdapter
    private var historique: MutableList<Recherche> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_citation_historique)

        try {
            var obj = JSONObject(loadJSON(this))
            val arr = obj.getJSONArray("historique")
            Log.i("array size: ", arr.length().toString())
            for (i in 0 until arr.length()) {
                val dateFormat = SimpleDateFormat("dd-MM-yyyy hh:mm:ss")
                val dateTime: Date = dateFormat.parse(arr.getJSONObject(i)["date"] as String)
                historique.add(Recherche(arr.getJSONObject(i)["recherche"] as String, dateTime))
            }

            if(historique.size > 0) historique = historique.sortedByDescending { it.date } as MutableList<Recherche>
            historiqueRv = findViewById<View>(R.id.list_item) as RecyclerView
            historiqueAdapter = RechercheAdapter(this)
            historiqueRv.apply {
                adapter = historiqueAdapter
            }
            historiqueAdapter.recherches = historique
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

}