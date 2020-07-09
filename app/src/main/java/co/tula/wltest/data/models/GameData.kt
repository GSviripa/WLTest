package co.tula.wltest.data.models

import com.google.gson.annotations.SerializedName

data class GameData(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("box_art_url") val artUrl: String
)