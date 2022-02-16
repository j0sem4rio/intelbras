package br.com.intelbras.view.activity.detail

interface OperationCallback<T> {
    fun onSuccess(data: T?)
    fun onError(error: String?)
}