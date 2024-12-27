package com.example.newsapp.models

data class News(
    val id: String,
    val title: String,
    val description: String,
    val content: String,
    val author: String?,
    val publishedAt: String,
    val url: String,
    val urlToImage: String?
)
