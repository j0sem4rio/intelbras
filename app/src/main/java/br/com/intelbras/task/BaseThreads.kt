package br.com.intelbras.task

import android.util.Log


open class BaseThreads {
    fun fail(msg: String) {
        Log.e("threads", msg)

    }
}