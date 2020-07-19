package com.example.wrcsearchfilter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.wrcsearchfilter.data.model.Auteur;
import com.example.wrcsearchfilter.ressource.api.retrofit.JsonPlaceHolderApiI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthorActivity extends AppCompatActivity {


    private TextView textViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8081")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApiI jsonPlaceHolderApiI = retrofit.create(JsonPlaceHolderApiI.class);
        Call<List<Auteur>> call = jsonPlaceHolderApiI.getAuthors();

        call.enqueue(new Callback<List<Auteur>>() {
            @Override
            public void onResponse(Call<List<Auteur>> call, Response<List<Auteur>> response) {
                if(!response.isSuccessful()){

                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Auteur> authors = response.body();

                for (Auteur author :   authors) {

                    String content = ""
                            + "ID: " + author.getId() + "\n"
                            + "Name: " + author.getNom() + "\n"
                            + "SurName: " + author.getPrenom() + "\n";
                            //+ "Livre: " + author.getBook() + "\n";

                    textViewResult.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Auteur>> call, Throwable throwable) {
                textViewResult.setText(throwable.getMessage());

            }
        });
    }
}