package co.tula.wltest.domain

import co.tula.wltest.data.DomainException
import co.tula.wltest.data.EitherE
import co.tula.wltest.data.TwitchApi
import co.tula.wltest.domain.models.CompositeData
import co.tula.wltest.domain.models.mapCompositeModel
import co.tula.wltest.utils.Left
import co.tula.wltest.utils.Right
import co.tula.wltest.utils.bind
import co.tula.wltest.utils.debug
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class TwitchRepository(val api: TwitchApi) {

    suspend fun getStreams(cursor: String? = null) = makeRequest {
        api.getStreams(cursor)
    }

    suspend fun getUsers(userIds: List<Long>) = makeRequest {
        api.getUsers(userIds)
    }

    suspend fun getGames(gameIds: List<Long>) = makeRequest {
        api.getGames(gameIds)
    }

    suspend fun loadCompositeData(cursor: String? = null) =
        getStreams(cursor).bind { streamResponse ->
            getUsers(streamResponse.dataList.map { it.userId })
                .bind { userResponse ->
                    when (val gameData = getGames(streamResponse.dataList.map { it.gameId })) {
                        is Right -> Right(
                            streamResponse.dataList.sortedBy { it.userId }
                                .zip(userResponse.dataList.sortedBy { it.id })
                                .map {
                                    mapCompositeModel(
                                        it.first,
                                        it.second,
                                        gameData.value.dataList.firstOrNull { gameData ->
                                            try {

                                                gameData.id.toLong()
                                            } catch (e: NumberFormatException) {
                                                0L
                                            } == it.first.gameId
                                        }
                                            ?: return Left(DomainException.UnknownException)
                                    )
                                }.let { CompositeData(it, streamResponse.pagination?.cursor) })
                        is Left -> gameData
                    }
                }
        }

    private suspend inline fun <T> makeRequest(crossinline block: suspend () -> T): EitherE<T> =
        withContext(Dispatchers.IO) {
            try {
                Right(block())
            } catch (e: CancellationException) {
                Left(DomainException.CanceledException)
            } catch (e: HttpException) {
                when (e.code()) {
                    401 -> Left(DomainException.UnauthorizedException)
                    else -> Left(DomainException.UnknownException)
                }
            } catch (e: Exception) {
                debug("UnknownException $e", e)
                Left(DomainException.UnknownException)
            }
        }
}