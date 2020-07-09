package co.tula.wltest.utils

import android.net.Uri
import android.text.TextUtils


fun Uri.getFragmentParametersMap(): Map<String, String>? {
    val fragment: String = fragment ?: return null
    val keyValuePairs: Array<String> =
        TextUtils.split(fragment, "&")
    val fragmentParameters: MutableMap<String, String> = mutableMapOf()
    for (keyValuePair in keyValuePairs) {
        val index: Int = keyValuePair.indexOf("=")
        val key = keyValuePair.substring(0, index)
        val value = keyValuePair.substring(index + 1)
        fragmentParameters[key] = value
    }
    return fragmentParameters
}