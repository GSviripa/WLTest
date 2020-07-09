package co.tula.wltest.domain

object AuthStorage {

    const val clientId = "gr7cvo14xwlguv89ak2qzfvxubwp86"
    const val redirectUri = "wlapp://twitch"

    const val authUrl =
        "https://id.twitch.tv/oauth2/authorize?client_id=$clientId&redirect_uri=$redirectUri&response_type=token&scope=viewing_activity_read"
    var token: String? = null
}