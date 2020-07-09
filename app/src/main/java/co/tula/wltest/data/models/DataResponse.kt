package co.tula.wltest.data.models

import com.google.gson.annotations.SerializedName

data class DataResponse<Data>(
    @SerializedName("data") val dataList: List<Data>,
    @SerializedName("pagination") val pagination: Pagination?
)