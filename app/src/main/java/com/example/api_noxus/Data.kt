package com.example.api_noxus

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty


@JsonIgnoreProperties(ignoreUnknown = true)
data class Champ(
    @JsonProperty ("name") val name :String="",
    @JsonProperty ("difficulty") val diff :String="",
    @JsonProperty ("image") val img : String="",
    @JsonProperty ("title") val title : String=""
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Role(
    @JsonProperty ("name") val name: String,
    @JsonProperty ("description") val desc: String,
    @JsonProperty ("image") val img: String,
)