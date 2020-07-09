package co.tula.wltest.presentation.models

import co.tula.wltest.domain.models.CompositeModel

sealed class StreamElement {
    data class MainStreamElement(val model: CompositeModel, var like: Boolean = false) : StreamElement()
}