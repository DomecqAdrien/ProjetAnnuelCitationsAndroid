package com.example.wrcsearchfilter.ressource.api.retrofit;

import com.example.wrcsearchfilter.data.model.Author;
import com.example.wrcsearchfilter.data.model.Citations;
import com.example.wrcsearchfilter.data.model.Livre;
import com.example.wrcsearchfilter.ressource.api.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApiI {

    @GET("Authors")
    Call<List<Author>> getAuthors();

    @GET("CitationByText")
    Call<List<Citations>> getCitations();

    @GET("Livres")
    Call<List<Livre>> getLivres();
}
