package com.example.wrcsearchfilter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.wrcsearchfilter.data.model.Book;
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
                .baseUrl("http://127.0.0.1:8081")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApiI jsonPlaceHolderApiI = retrofit.create(JsonPlaceHolderApiI.class);
        Call<List<Book>> call = jsonPlaceHolderApiI.getLivres();

        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if(!response.isSuccessful()){

                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Book> books = response.body();

                for (Book book : books) {

                    String content = ""
                            + "ID: " + book.getId() + "\n"
                            + "Title: " + book.getTitre() + "\n"
                            + "Année de sortie: " + book.getAnneeParution() + "\n"
                            + "Auteur: " + book.getAuteur() + "\n\n";

                    textViewResult.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable throwable) {
                textViewResult.setText(throwable.getMessage());

            }
        });

    }
}
