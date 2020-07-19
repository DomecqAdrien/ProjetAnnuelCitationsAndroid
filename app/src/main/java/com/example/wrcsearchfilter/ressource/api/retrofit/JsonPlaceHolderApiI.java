package com.example.wrcsearchfilter.ressource.api.retrofit;

import com.example.wrcsearchfilter.data.model.Auteur;
import com.example.wrcsearchfilter.data.model.Book;
import com.example.wrcsearchfilter.data.model.Citation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApiI {

    @GET("Authors")
    Call<List<Auteur>> getAuthors();

    @POST("/citation/getCitationByText")
    Call<Citation> getCitation(@Body String recherche);

    @GET("Livres")
    Call<List<Book>> getLivres();
}
