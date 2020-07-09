package co.tula.wltest.presentation

import androidx.recyclerview.widget.DiffUtil
import co.tula.wltest.presentation.models.StreamElement

class PagedListItemCallback : DiffUtil.ItemCallback<StreamElement>() {
    override fun areItemsTheSame(oldItem: StreamElement, newItem: StreamElement): Boolean {
        return when (oldItem) {
            is StreamElement.MainStreamElement -> {
                (newItem is StreamElement.MainStreamElement
                        && newItem.model.streamId == oldItem.model.streamId)
            }
        }
    }

    override fun areContentsTheSame(
        oldItem: StreamElement,
        newItem: StreamElement
    ): Boolean {
        return when (oldItem
            ) {
            is StreamElement.MainStreamElement -> {
                (newItem is StreamElement.MainStreamElement
                        && newItem.model == oldItem.model)
            }
        }
    }
}