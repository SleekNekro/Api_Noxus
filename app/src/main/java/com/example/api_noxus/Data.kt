package com.example.api_noxus

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable


@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
data class Champ(
    @PrimaryKey
    @JsonProperty ("id") val id : Int,
    @JsonProperty ("name") val name :String,
    @JsonProperty ("difficulty") val diff :String,
    @JsonProperty ("image") val img : String,
    @JsonProperty ("title") val title : String
) : Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
data class Role(
    @PrimaryKey
    @JsonProperty ("id") val id : Int,
    @JsonProperty ("name") val name: String,
    @JsonProperty ("description") val desc: String,
    @JsonProperty ("image") val img: String,
)