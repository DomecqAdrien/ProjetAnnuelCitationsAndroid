package com.example.wrcsearchfilter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wrcsearchfilter.data.model.Citation;
import com.example.wrcsearchfilter.ressource.api.retrofit.JsonPlaceHolderApiI;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CitationsActivity extends AppCompatActivity {
    private Citation citation;
    private TextView textViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citations);
        textViewResult = findViewById(R.id.text_view_resultsx);
        String recherche = this.getIntent().getStringExtra("recherche");

        Log.i("recherche",recherche);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://0a529e218b86.ngrok.io")
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
                citation = response.body();
                Log.i("citation", citation.toString());

                TextView author = findViewById(R.id.book_author);
                author.setText(citation.getBook().getAuteur().getPrenom()+ ' '+citation.getBook().getAuteur().getNom());

                TextView title = findViewById(R.id.book_title);
                title.setText(citation.getBook().getTitre());

                TextView anneeParutionLivre = findViewById(R.id.book_annee_parution);
                anneeParutionLivre.setText(String.valueOf(citation.getBook().getAnneeParution()));

                TextView citationTv = findViewById(R.id.text_citation);
                citationTv.setText(citation.getCitation());

                ImageView bookImg = findViewById(R.id.book_img);
                Glide.with(bookImg).load(citation.getBook().getImageUrl()).into(bookImg);

                TextView tagsTv = findViewById(R.id.citation_tags);
                tagsTv.setText(tagsTv.getText()+citation.getTags());

                if(citation.getCitationsConnexes() != null){
                    for (Citation citationConnexe : citation.getCitationsConnexes()) {

                        String content = ""
                                + "Livre: " + citationConnexe.getBook().getTitre() + "\n"
                                + "Citation: " + citationConnexe.getCitation() + "\n"
                                + "Catégorie(s) similaire(s): " + citationConnexe.getTags() + "\n\n";

                        textViewResult.append(content);
                    }
                }


            }

            @Override
            public void onFailure(Call<Citation> call, Throwable throwable) {
                Log.i("error:",throwable.getMessage());
                textViewResult.setText("Aucune citation trouvée.");

            }
        });

    }

    public void showOtherBooks(View v){
        Intent intent = new Intent(this, LivreActivity.class);
        intent.putExtra("books", (Parcelable) citation.getBook().getAuteur().getBooks());
        this.startActivity(intent);
    }
}