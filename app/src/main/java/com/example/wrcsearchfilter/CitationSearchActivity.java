package com.example.wrcsearchfilter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CitationSearchActivity extends AppCompatActivity {

    TextView textCitation;
    Button buttonRechercher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citation_search);

        textCitation = findViewById(R.id.textCitation);
        buttonRechercher = findViewById(R.id.buttonSearch);
    }

    public void searchCitation(View v) throws JSONException {
        JSONObject obj = new JSONObject(JsonManager.loadJSON(this));
        Log.i("json",obj.toString());
        JSONArray arr = obj.getJSONArray("historique");
        JSONObject newFav = new JSONObject();
        Date today = new Date();
        DateFormat shortDateFormat = DateFormat.getDateTimeInstance(
                DateFormat.SHORT,
                DateFormat.SHORT);
        Date date = Calendar.getInstance().getTime();
        DateFormat d = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        newFav.put("recherche", textCitation.getText().toString()).put("date", d.format(today));
        arr.put(newFav);
        JsonManager.writeJSON(this,obj.toString());
        Intent intent = new Intent(this, CitationResultActivity.class);
        intent.putExtra("recherche",textCitation.getText().toString());
        this.startActivity(intent);
    }
}
