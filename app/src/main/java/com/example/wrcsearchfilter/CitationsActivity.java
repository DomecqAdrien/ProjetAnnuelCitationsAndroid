package com.example.wrcsearchfilter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.wrcsearchfilter.data.model.Citations;
import com.example.wrcsearchfilter.data.model.Livre;
import com.example.wrcsearchfilter.ressource.api.retrofit.JsonPlaceHolderApiI;

import java.util.List;

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


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8081")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApiI jsonPlaceHolderApiI = retrofit.create(JsonPlaceHolderApiI.class);
        Call<List<Citations>> call = jsonPlaceHolderApiI.getCitations();

        call.enqueue(new Callback<List<Citations>>() {
            @Override
            public void onResponse(Call<List<Citations>> call, Response<List<Citations>> response) {
                if(!response.isSuccessful()){

                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Citations> citations = response.body();

                for (Citations citation : citations) {

                    String content = ""
                            + "ID: " + citation.getId() + "\n"
                            + "Livre: " + citation.getLivre() + "\n"
                            + "Contenu: " + citation.getContenu() + "\n";

                    textViewResult.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Citations>> call, Throwable throwable) {
                textViewResult.setText(throwable.getMessage());

            }
        });

    }
}