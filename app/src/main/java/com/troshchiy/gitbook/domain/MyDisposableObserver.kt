package com.troshchiy.gitbook.domain

import com.tickengo.rider.base_mvp_interfaces.LoadDataView
import com.troshchiy.gitbook.extensions.logD
import io.reactivex.observers.DisposableObserver
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.lang.ref.WeakReference
import java.net.SocketTimeoutException

abstract class MyDisposableObserver<T>(val view: LoadDataView) : DisposableObserver<T>() {
    private val tag = this.javaClass.simpleName

    private val loadDataView: WeakReference<LoadDataView> = WeakReference(view)

    protected abstract fun onSuccess(data: T)

    override fun onComplete() {}

    override fun onNext(data: T) {
        // You can return StatusCodes of different cases from your API and handle it here.
        // I usually include these cases on BaseResponse and iherit it from every Response
        onSuccess(data)
    }

    override fun onError(t: Throwable) {
        val view = loadDataView.get()
        when (t) {
            is HttpException -> {
                val responseBody = t.response().errorBody()
                val errorMessage = if (responseBody != null) errorMessage(responseBody) else ""
                view?.showError(errorMessage)
                logD(tag, "HttpException: $errorMessage")

            }
            is SocketTimeoutException -> {
                //                view.onTimeout()
                logD(tag, "SocketTimeoutException: ${t.message}")
            }
            is IOException -> {
                //            view.onNetworkError()
                logD(tag, "IOException: ${t.message}")
            }
            else -> {
                //                view.onUnknownError(t.message)
                logD(tag, "onUnknownError: ${t.message}")
            }
        }
    }

    private fun errorMessage(responseBody: ResponseBody): String = try {
        JSONObject(responseBody.string()).getString("message")
    } catch (e: Exception) {
        e.message.toString()
    }
}