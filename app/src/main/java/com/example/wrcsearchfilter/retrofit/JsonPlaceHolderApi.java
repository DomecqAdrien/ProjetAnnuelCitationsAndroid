package com.example.wrcsearchfilter.retrofit;

import com.example.wrcsearchfilter.model.Auteur;
import com.example.wrcsearchfilter.model.Book;
import com.example.wrcsearchfilter.model.Citation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @GET("Authors")
    Call<List<Auteur>> getAuthors();

    @POST("/citation/getCitationByText")
    Call<Citation> getCitation(@Body String recherche);

    @GET("Livres")
    Call<List<Book>> getLivres();
}
