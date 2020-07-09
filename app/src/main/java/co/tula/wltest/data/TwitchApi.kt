package co.tula.wltest.data

import co.tula.wltest.data.models.DataResponse
import co.tula.wltest.data.models.GameData
import co.tula.wltest.data.models.StreamData
import co.tula.wltest.data.models.UserData
import retrofit2.http.GET
import retrofit2.http.Query

interface TwitchApi {

    @GET("/helix/users")
    suspend fun getUsers(@Query("id") userIds: List<Long>): DataResponse<UserData>

    @GET("/helix/streams")
    suspend fun getStreams(@Query("after") cursor: String? = null): DataResponse<StreamData>

    @GET("/helix/games")
    suspend fun getGames(@Query("id") gameIds: List<Long>): DataResponse<GameData>
}