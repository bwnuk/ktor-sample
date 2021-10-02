package com.github.wnuk.repositories

import com.github.wnuk.entities.Article
import com.github.wnuk.factory.ArticleFactory
import com.github.wnuk.sources.ArticleSource
import java.util.*

object ArticleRepository {

    fun getAllArticles(): List<Article> {
        isMade()
        return ArticleSource.articles
    }

    fun getArticles(start: Int, chunk: Int): List<Article> {
        isMade()
        return ArticleSource.articles.subList(start, start + chunk)
    }

    fun getArticle(id: Int): Article? {
        isMade()
        return if (id > ArticleSource.articles.size) {
            null
        } else {
            ArticleSource.articles[id]
        }
    }

    fun addArticle(
        title: String,
        desc: String,
        date: Date
    ): Article {
        isMade()
        return ArticleSource.addArticle(title, desc, date)
    }

    fun deleteArticle(id: Int): Boolean {
        isMade()
        if (ArticleSource.articles.size < id) {
            return false
        }
        ArticleSource.articles.removeAt(id)
        return true
    }

    private fun isMade() {
        if (!ArticleSource.isMade()) {
            ArticleFactory.createMock()
        }
    }
}