package co.tula.wltest.presentation

import android.content.Intent
import co.tula.wltest.CommonHolder
import co.tula.wltest.domain.AuthStorage
import co.tula.wltest.domain.models.CompositeModel
import co.tula.wltest.utils.Left
import co.tula.wltest.utils.Right
import co.tula.wltest.utils.debug
import co.tula.wltest.utils.getFragmentParametersMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainPresenter {
    var nextCursor: String? = null
    var showError: (String) -> Unit = {}
    var initAdapter: () -> Unit = {}
    var afterSignInUpdate: () -> Unit = {}

    fun loadNext(onResult: (List<CompositeModel>) -> Unit) {
        GlobalScope.launch {
            when (val result = CommonHolder.twitchRepository.loadCompositeData(nextCursor)) {
                is Right -> withContext(Dispatchers.Main) {
                    nextCursor = result.value.pagination
                    onResult(result.value.models)
                }
                is Left -> showError(result.value.message ?: "Unknown Error")
            }
        }
    }

    fun onGetData(intent: Intent) {
        val uri = intent.data
        if (uri != null && uri.toString().startsWith(AuthStorage.redirectUri)) {
            AuthStorage.token = uri.getFragmentParametersMap()?.get("access_token")
            initAdapter()
            afterSignInUpdate()
            debug(AuthStorage.token ?: "no token")
        }
    }
}
