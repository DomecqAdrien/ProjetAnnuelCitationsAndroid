package com.example.wrcsearchfilter;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static androidx.appcompat.R.id.search_src_text;

public class FavorisActivity extends AppCompatActivity {

    SearchView searchView;
    RecyclerView filmshow;
    List<Item> BestCitationlist = new ArrayList<>();
    MainActivityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoris);

        BestCitationlist.add(new Item("Harry Potter & la coupe de feu",R.drawable.harry_potter));
        BestCitationlist.add(new Item("Labyrinthe de Pan",R.drawable.le_labyrinthe_de_pan));
        BestCitationlist.add(new Item("Labyrinthe",R.drawable.le_labyrinthe));
        BestCitationlist.add(new Item("Hunger Games",R.drawable.hunger_games_tome_1));
        BestCitationlist.add(new Item("Games of thrones",R.drawable.game_of_throne));
        filmshow = (RecyclerView) findViewById(R.id.listshow);
        filmshow.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        filmshow.setLayoutManager(linearLayoutManager);
        adapter = new MainActivityAdapter(BestCitationlist,FavorisActivity.this);
        filmshow.setAdapter(adapter);
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

                final List<Item> filtermodelist=filter( BestCitationlist,newText);
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
