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

        final Button HistoriqueButton = findViewById(R.id.Historique);
        final Button FavorisButton = findViewById(R.id.Favoris);
        final Button RechercheButton = findViewById(R.id.Recherhe);

        FavorisButton.setOnClickListener(v -> {

            Intent i = new Intent(MainPageActivity.this, CitationFavorisActivity.class);
            startActivity(i);

        });

        HistoriqueButton.setOnClickListener(v -> {

            Intent i = new Intent(MainPageActivity.this, CitationHistoriqueActivity.class);
            startActivity(i);

        });

        RechercheButton.setOnClickListener(v -> {
            Intent i = new Intent(MainPageActivity.this, CitationSearchActivity.class);
            startActivity(i);

        });
    }

}
