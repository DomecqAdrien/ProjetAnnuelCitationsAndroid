package com.example.citationAppliMobile.model

import com.google.gson.annotations.SerializedName

data class APIParserDTO (
        @SerializedName("statut") val statut: String?=null,
        @SerializedName("citations") val citations: List<Citation>?=null,
        @SerializedName("citation") val citation: Citation?=null
)
