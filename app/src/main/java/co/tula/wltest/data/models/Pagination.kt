package co.tula.wltest.data.models

import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("cursor") val cursor: String
)