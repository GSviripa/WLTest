package co.tula.wltest.domain.models

data class CompositeModel(
    val streamId: Long,
    val userId: Long,
    val gameId: Long,
    val userName: String,
    val streamUrl: String,
    val title: String,
    val thumbnailUrl: String,
    val profileImageUrl: String,
    val gameImageUrl: String
)