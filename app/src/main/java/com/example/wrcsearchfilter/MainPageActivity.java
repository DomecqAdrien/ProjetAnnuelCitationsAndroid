package com.example.wrcsearchfilter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainPageActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
    }

    public void showSearch(View v){
        Intent i = new Intent(MainPageActivity.this, CitationSearchActivity.class);
        startActivity(i);
    }

    public void showHistorique(View v){
        Intent i = new Intent(MainPageActivity.this, CitationHistoriqueActivity.class);
        startActivity(i);
    }

    public void showFavoris(View v){
        Intent i = new Intent(MainPageActivity.this, CitationFavorisActivity.class);
        startActivity(i);
    }

}
