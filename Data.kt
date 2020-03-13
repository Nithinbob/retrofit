package com.example.movielibrary.ui.main

import com.google.gson.annotations.SerializedName

data class Data(@SerializedName("userId") val userId: Int,
                @SerializedName("id") val id: Int,
                @SerializedName("title") val title: String,
                @SerializedName("body") val body: String) {
}