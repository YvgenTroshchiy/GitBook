package com.troshchiy.gitbook.network.models

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize data class Books(
        val list: List<Book>,
        val total: Int,
        val limit: Int,
        val page: Int,
        val pages: Int
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize data class Book(
        val title: String,
        val description: String,
        val cover: Cover,
        val urls: Urls,
        val counts: Counts
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize data class Cover(val large: String, val small: String) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize data class Urls(val homepage: String) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize data class Counts(val stars: Int) : Parcelable