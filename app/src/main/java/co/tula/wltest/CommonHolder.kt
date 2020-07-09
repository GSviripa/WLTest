package co.tula.wltest

import co.tula.wltest.data.ApiProvider
import co.tula.wltest.domain.TwitchRepository

object CommonHolder {
    private val apiProvider = ApiProvider("https://api.twitch.tv")
    val twitchRepository = TwitchRepository(apiProvider.twitchApi)
}