package co.tula.wltest.domain.models

data class CompositeData(
    val models: List<CompositeModel>,
    val pagination: String?
)