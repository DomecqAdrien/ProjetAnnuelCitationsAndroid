package com.example.wrcsearchfilter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class BoardActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_x);

        final Button HistoriqueButton = findViewById(R.id.Historique);
        final Button FavorisButton = findViewById(R.id.Favoris);
        final Button RechercheButton = findViewById(R.id.Recherhe);

        FavorisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(BoardActivity.this,FavorisActivity.class);
                startActivity(i);

            }
        });

        HistoriqueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(BoardActivity.this,HistoriqueActivity.class);
                startActivity(i);

            }
        });

        RechercheButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BoardActivity.this,MainActivity.class);
                startActivity(i);

            }
        });
    }

}
