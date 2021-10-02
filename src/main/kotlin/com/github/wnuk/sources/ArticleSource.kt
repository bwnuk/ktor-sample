package com.github.wnuk.sources

import com.github.wnuk.entities.Article
import java.util.*

object ArticleSource {
    val articles = mutableListOf<Article>()

    fun isMade(): Boolean = articles.size > 0

    fun addArticle(
        title: String,
        desc: String,
        date: Date
    ): Article {
        val article = Article((articles.size + 1), title, desc, date)
        articles.add(article)
        return article
    }
}
