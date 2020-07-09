package co.tula.wltest.presentation

import androidx.paging.PositionalDataSource
import co.tula.wltest.presentation.models.StreamElement

class StreamsDataSource(val presenter: MainPresenter) :
    PositionalDataSource<StreamElement>() {

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<StreamElement>) {
        presenter.loadNext { callback.onResult(it.map { StreamElement.MainStreamElement(it) }) }
    }

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<StreamElement>
    ) {
        presenter.loadNext {
            callback.onResult(
                it.map { StreamElement.MainStreamElement(it) },
                it.size
            )
        }
    }
}
