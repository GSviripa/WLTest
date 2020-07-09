package co.tula.wltest.domain.models

import co.tula.wltest.data.models.GameData
import co.tula.wltest.data.models.StreamData
import co.tula.wltest.data.models.UserData

fun mapCompositeModel(
    streamData: StreamData,
    userData: UserData,
    gameData: GameData
): CompositeModel = CompositeModel(
    streamData.id,
    userData.id,
    try {
        gameData.id.toLong()
    } catch (e: NumberFormatException) {
        0L
    },
    "@${userData.displayName}",
    "https://twitch.tv/${userData.displayName}",
    streamData.title,
    streamData.thumbnailUrl.replace("-{width}x{height}", ""),
    userData.profileImageUrl,
    gameData.artUrl.replace("-{width}x{height}", "")
)
