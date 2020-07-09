package co.tula.wltest.data.models

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("id") val id: Long,
    @SerializedName("login") val login: String,
    @SerializedName("display_name") val displayName: String,
    @SerializedName("profile_image_url") val profileImageUrl: String
)
