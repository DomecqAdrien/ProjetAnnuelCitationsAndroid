package com.example.wrcsearchfilter

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.EditText
import androidx.appcompat.R.id
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.example.wrcsearchfilter.adapter.RechercheAdapter
import com.example.wrcsearchfilter.model.Citation
import com.example.wrcsearchfilter.model.Recherche
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class CitationFavorisActivity : AppCompatActivity() {
    var searchView: SearchView? = null

    private lateinit var historiqueRv: RecyclerView
    private lateinit var historiqueAdapter: RechercheAdapter
    private val historique: MutableList<Recherche> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_citation_favoris)


        try {
            var obj = JSONObject(JsonManager.loadJSON(this))
            val arr = obj.getJSONArray("favoris")
            Log.i("array size: ", arr.length().toString())
            for (i in 0 until arr.length()) {
                val dateFormat = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
                val dateTime: Date = dateFormat.parse(arr.getJSONObject(i)["date"] as String)
                historique.add(Recherche(arr.getJSONObject(i)["citation"] as String, dateTime))
            }
            historiqueRv = findViewById<View>(R.id.list_item_favoris) as RecyclerView
            historiqueAdapter = RechercheAdapter(this)
            historiqueRv.apply {
                adapter = historiqueAdapter
            }
            historiqueAdapter.recherches = historique
        } catch (e: JSONException) {
            e.printStackTrace()
        }



        /*BestCitationlist.add(Item("Harry Potter & la coupe de feu", R.drawable.harry_potter))
        BestCitationlist.add(Item("Labyrinthe de Pan", R.drawable.le_labyrinthe_de_pan))
        BestCitationlist.add(Item("Labyrinthe", R.drawable.le_labyrinthe))
        BestCitationlist.add(Item("Hunger Games", R.drawable.hunger_games_tome_1))
        BestCitationlist.add(Item("Games of thrones", R.drawable.game_of_throne))
        filmshow = findViewById<View>(R.id.listshow) as RecyclerView
        filmshow!!.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(this)
        filmshow!!.layoutManager = linearLayoutManager
        adapter = MainActivityAdapter(BestCitationlist, this@FavorisActivity)
        filmshow!!.adapter = adapter*/
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.searchfile, menu)
        val myActionMenuItem = menu.findItem(R.id.search)
        searchView = myActionMenuItem.actionView as SearchView
        (searchView!!.findViewById<View>(id.search_src_text) as EditText).setHintTextColor(resources.getColor(R.color.white))
        searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (!searchView!!.isIconified) {
                    searchView!!.isIconified = true
                }
                myActionMenuItem.collapseActionView()
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                //val filtermodelist = filter(BestCitationlist, newText)
                //adapter!!.setfilter(filtermodelist)
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun filter(pl: List<Citation>, Query: String): List<Citation> {
        var Query = Query
        Query = Query.toLowerCase()
        val filterModeList: MutableList<Citation> = ArrayList()
        for (model in pl) {
            /*val text = model.name.toLowerCase()
            if (text.startsWith(Query)) {
                filterModeList.add(model)
            }*/
        }
        return filterModeList
    }
}