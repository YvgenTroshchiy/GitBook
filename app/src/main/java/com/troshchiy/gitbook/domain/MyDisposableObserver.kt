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

//TODO: Rename, maybe CallbackWrapper, DisposableErrorableObserver ?
abstract class MyDisposableObserver<T>(val view: LoadDataView) : DisposableObserver<T>() {
    private val tag = this.javaClass.simpleName

    private val loadDataView: WeakReference<LoadDataView> = WeakReference(view)

    protected abstract fun onSuccess(data: T)

    override fun onComplete() {
        view.hideLoadingProgress()
    }

    override fun onNext(data: T) {
        view.hideLoadingProgress()

        // You can return StatusCodes of different cases from your API and handle it here.
        // I usually include these cases on BaseResponse and iherit it from every Response
        onSuccess(data)
    }

    override fun onError(t: Throwable) {
        view.hideLoadingProgress()

        val view = loadDataView.get()
        when (t) {
            is HttpException -> {
                val responseBody = t.response().errorBody()
                val errorMessage = if (responseBody != null) errorMessage(responseBody) else ""
                logD(tag, "HttpException: $errorMessage")
                view?.showError(errorMessage)
            }
            is SocketTimeoutException -> {
                logD(tag, "SocketTimeoutException: ${t.message}")
                view?.showError("SocketTimeoutException")
            }
            is IOException -> {
                logD(tag, "IOException: ${t.message}")
                view?.showError("IOException")
            }
            else -> {
                logD(tag, "onUnknownError: ${t.message}")
                view?.showError("onUnknownError")
            }
        }
    }

    private fun errorMessage(responseBody: ResponseBody): String = try {
        JSONObject(responseBody.string()).getString("message")
    } catch (e: Exception) {
        e.message.toString()
    }
}