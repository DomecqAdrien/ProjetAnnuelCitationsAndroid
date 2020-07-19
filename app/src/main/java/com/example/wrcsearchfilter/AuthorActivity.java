package com.example.wrcsearchfilter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.wrcsearchfilter.data.model.Author;
import com.example.wrcsearchfilter.data.model.Citations;
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
        Call<List<Author>> call = jsonPlaceHolderApiI.getAuthors();

        call.enqueue(new Callback<List<Author>>() {
            @Override
            public void onResponse(Call<List<Author>> call, Response<List<Author>> response) {
                if(!response.isSuccessful()){

                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Author> authors = response.body();

                for (Author author :   authors) {

                    String content = ""
                            + "ID: " + author.getId() + "\n"
                            + "Name: " + author.getName() + "\n"
                            + "SurName: " + author.getSurname() + "\n"
                            + "Livre: " + author.getLivre() + "\n";

                    textViewResult.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Author>> call, Throwable throwable) {
                textViewResult.setText(throwable.getMessage());

            }
        });
    }
}