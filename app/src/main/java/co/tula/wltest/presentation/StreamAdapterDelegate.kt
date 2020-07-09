package co.tula.wltest.presentation

import androidx.core.content.ContextCompat
import co.tula.wltest.GlideApp
import co.tula.wltest.R
import co.tula.wltest.presentation.models.StreamElement
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import kotlinx.android.synthetic.main.item_stream.view.*

fun streamAdapterDelegate(onElementShare: (url: String) -> Unit, onElementComment: () -> Unit) =
    adapterDelegateLayoutContainer<StreamElement.MainStreamElement, StreamElement>(R.layout.item_stream) {

        bind {
            GlideApp.with(itemView).load(item.model.thumbnailUrl)
                .fitCenter()
                .into(itemView.streamThumbnailImageView)
            GlideApp.with(itemView).load(item.model.gameImageUrl)
                .fitCenter()
                .into(itemView.gameImageView)
            GlideApp.with(itemView).load(item.model.profileImageUrl)
                .transform(CircleCrop())
                .into(itemView.avatarImageView)
            itemView.userNameTextView.text = item.model.userName
            itemView.descriptionTextView.text = item.model.title
            itemView.likeImageView.setColorFilter(
                ContextCompat.getColor(
                    itemView.context,
                    if (item.like) R.color.twitchPurple else R.color.colorAccent
                )
            )

            itemView.shareImageView.setOnClickListener { onElementShare(item.model.streamUrl) }
            itemView.likeImageView.setOnClickListener {
                itemView.likeImageView.setColorFilter(
                    ContextCompat.getColor(itemView.context, R.color.twitchPurple)
                )
            }

            itemView.commentImageView.setOnClickListener { onElementComment() }
        }
    }