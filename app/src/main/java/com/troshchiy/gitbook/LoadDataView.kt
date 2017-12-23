package com.tickengo.rider.base_mvp_interfaces

public interface LoadDataView {
    fun showLoadingProgress()
    fun hideLoadingProgress()

    fun showError(message: String)
    fun hideError()
}