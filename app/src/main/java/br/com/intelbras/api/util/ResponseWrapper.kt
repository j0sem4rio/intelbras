package br.com.intelbras.api.util

class ResponseWrapper(var message: String) {

    companion object {
        const val ID_SUCCESS = 1
        const val ID_FAILURE = 2
        const val ID_OFFLINE = 3
    }
}