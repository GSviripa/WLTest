package co.tula.wltest.data.models

import com.google.gson.annotations.SerializedName

data class StreamData(
    @SerializedName("id") val id: Long,
    @SerializedName("user_id") val userId: Long,
    @SerializedName("game_id") val gameId: Long,
    @SerializedName("user_name") val userName: String,
    @SerializedName("type") val type: String,
    @SerializedName("title") val title: String,
    @SerializedName("viewer_count") val viewerCount: Int,
    @SerializedName("started_at") val startedAt: String,
    @SerializedName("language") val language: String,
    @SerializedName("thumbnail_url") val thumbnailUrl: String
)