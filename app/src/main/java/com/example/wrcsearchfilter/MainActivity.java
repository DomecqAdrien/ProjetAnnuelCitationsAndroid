package com.example.wrcsearchfilter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import java.util.ArrayList;
import java.util.List;
import static androidx.appcompat.R.id.search_src_text;
import static androidx.appcompat.R.id.text;

public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    RecyclerView listshowrcy;
    List<Item> productlist = new ArrayList<>();
    MainActivityAdapter adapter;
    TextView textCitation;
    Button buttonRechercher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productlist.add(new Item("Harry Potter & la coupe de feu",R.drawable.harry_potter));
        productlist.add(new Item("Labyrinthe de Pan",R.drawable.le_labyrinthe_de_pan));
        productlist.add(new Item("Labyrinthe",R.drawable.le_labyrinthe));
        productlist.add(new Item("Hunger Games",R.drawable.hunger_games_tome_1));
        productlist.add(new Item("Games of thrones",R.drawable.game_of_throne));
        listshowrcy = (RecyclerView) findViewById(R.id.listshow);
        listshowrcy.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listshowrcy.setLayoutManager(linearLayoutManager);
        adapter = new MainActivityAdapter(productlist,MainActivity.this);
        listshowrcy.setAdapter(adapter);
        textCitation = findViewById(R.id.textCitation);
        buttonRechercher = findViewById(R.id.buttonSearch);



    }

    public void searchCitation(View v){
        Intent intent = new Intent(this, CitationsActivity.class);
        intent.putExtra("recherche",textCitation.getText().toString());
        this.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.searchfile,menu);
        final MenuItem myActionMenuItem=menu.findItem(R.id.search);
        searchView=(SearchView) myActionMenuItem.getActionView();

        ((EditText) searchView.findViewById(search_src_text)).setHintTextColor(getResources().getColor(R.color.white));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(!searchView.isIconified()){
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();

                return false;
            }


            @Override
            public boolean onQueryTextChange(String newText) {

                final  List<Item> filtermodelist=filter(productlist,newText);
                adapter.setfilter(filtermodelist);
                return true;

            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private List<Item> filter(List<Item> pl, String Query){
        Query = Query.toLowerCase();
        final List<Item> filterModeList = new ArrayList<>();
        for (Item model:pl)
        {
            final String text = model.getName().toLowerCase();
            if (text.startsWith(Query)){
                filterModeList.add(model);
            }
        }
        return filterModeList;
    }
}
