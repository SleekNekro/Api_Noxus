package com.example.api_noxus

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable


@Entity
class Champ: Serializable{
    @PrimaryKey
    @JsonProperty ("champion_id") var id : Int=0
    @JsonProperty ("name") var name :String=""
    @JsonProperty ("difficulty") var diff :String=""
    @JsonProperty ("image") var img : String=""
    @JsonProperty ("title") var title : String=""
}

@Entity
class Role: Serializable {
    @PrimaryKey
    @JsonProperty("role_id") var id: Int=0
    @JsonProperty("name") var name: String=""
    @JsonProperty("description") var desc: String=""
    @JsonProperty("img_rol") var img: String=""

}