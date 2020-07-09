package co.tula.wltest.utils

import android.util.Log

fun Any.debug(message: String, throwable: Throwable? = null) {
    Log.d(
        if (javaClass.simpleName.isNotEmpty()) javaClass.simpleName else javaClass.name,
        message + " [${Thread.currentThread()}][${this.hashCode().toString(16)}]",
        throwable
    )
}

fun Throwable.debug(message: String) {
    this.debug(message, this)
}