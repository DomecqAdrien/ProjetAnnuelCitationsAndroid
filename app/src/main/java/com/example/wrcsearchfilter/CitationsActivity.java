package com.example.wrcsearchfilter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.wrcsearchfilter.data.model.Citation;
import com.example.wrcsearchfilter.ressource.api.retrofit.JsonPlaceHolderApiI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CitationsActivity extends AppCompatActivity {

    private TextView textViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citations);
        textViewResult = findViewById(R.id.text_view_resultsx);
        String recherche = this.getIntent().getStringExtra("recherche");

        Log.i("recherche",recherche);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://f17cda351be9.ngrok.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApiI jsonPlaceHolderApiI = retrofit.create(JsonPlaceHolderApiI.class);
        Call<Citation> call = jsonPlaceHolderApiI.getCitation(recherche);

        call.enqueue(new Callback<Citation>() {
            @Override
            public void onResponse(Call<Citation> call, Response<Citation> response) {
                if(!response.isSuccessful()){
                    Log.i("response","fail");
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                Log.i("body",response.toString());
                Citation citation = response.body();
                Log.i("citation", citation.toString());

                TextView author = findViewById(R.id.author);
                author.setText(citation.getBook().getAuteur().getPrenom()+ ' '+citation.getBook().getAuteur().getNom());

                TextView anneeParutionLivre = findViewById(R.id.annee_parution);
                anneeParutionLivre.setText(citation.getBook().getAnneeParution());
                author.setText(citation.getBook().getAuteur().getPrenom()+ ' '+citation.getBook().getAuteur().getNom());

                TextView citationTv = findViewById(R.id.textCitation);
                citationTv.setText(citation.getCitation());
                anneeParutionLivre.setText(citation.getBook().getAnneeParution());

                for (Citation citationConnexe : citation.getCitationsConnexes()) {

                    String content = ""
                            + "ID: " + citationConnexe.getId() + "\n"
                            + "Livre: " + citationConnexe.getBook() + "\n"
                            + "Contenu: " + citationConnexe.getCitation() + "\n";

                    textViewResult.append(content);
                }

            }

            @Override
            public void onFailure(Call<Citation> call, Throwable throwable) {
                Log.i("error:",throwable.getMessage());
                textViewResult.setText("Aucune citation trouvée.");

            }
        });

    }
}