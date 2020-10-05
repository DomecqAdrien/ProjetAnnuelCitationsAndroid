package com.example.citationAppliMobile.service

import com.example.citationAppliMobile.model.APIParserDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CitationService {

    @GET("/citation/searchCitationsByText")
    fun searchCitationsByText(@Query("text") text: String): Call<String>

    @GET("/citation/getCitation")
    fun getCitation(@Query("citation") text: String): Call<String>

    @GET("/citation/getQueueResponse")
    fun getQueueResponse(@Query("queueName") queueName: String): Call<APIParserDTO>

}