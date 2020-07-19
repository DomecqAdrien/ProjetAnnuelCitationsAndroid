package com.example.wrcsearchfilter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.wrcsearchfilter.data.model.Livre;
import com.example.wrcsearchfilter.ressource.api.retrofit.JsonPlaceHolderApiI;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LivreActivity extends AppCompatActivity {

    private TextView textViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livre);
        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8081")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApiI jsonPlaceHolderApiI = retrofit.create(JsonPlaceHolderApiI.class);
        Call<List<Livre>> call = jsonPlaceHolderApiI.getLivres();

        call.enqueue(new Callback<List<Livre>>() {
            @Override
            public void onResponse(Call<List<Livre>> call, Response<List<Livre>> response) {
                if(!response.isSuccessful()){

                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Livre> livres = response.body();

                for (Livre livre : livres) {

                    String content = ""
                            + "ID: " + livre.getId() + "\n"
                            + "Title: " + livre.getTitle() + "\n"
                            + "Année de sortie: " + livre.getYears() + "\n"
                            + "Auteur: " + livre.getAuthor() + "\n\n";

                    textViewResult.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Livre>> call, Throwable throwable) {
                textViewResult.setText(throwable.getMessage());

            }
        });

    }
}
