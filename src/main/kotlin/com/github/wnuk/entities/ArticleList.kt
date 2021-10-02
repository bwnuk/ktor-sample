package com.github.wnuk.entities

data class ArticleList(
    val size: String,
    val start: String,
    val articles: List<Article?>
)
