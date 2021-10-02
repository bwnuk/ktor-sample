package com.github.wnuk.services

import com.github.wnuk.entities.Article
import com.github.wnuk.entities.ArticleList
import com.github.wnuk.entities.ArticleRequest
import com.github.wnuk.repositories.ArticleRepository

object ArticleServices {
    fun getAllArticles(): ArticleList {
        val articles = ArticleRepository.getAllArticles()
        return ArticleList(articles.size.toString(), "0", articles)
    }

    fun getArticles(start: Int, chunk: Int): ArticleList {
        val articles = ArticleRepository.getArticles(start, chunk)
        return ArticleList(articles.size.toString(), "0", articles)
    }

    fun getArticle(id: Int?): ArticleList? {
        return if (id != null) {
            val article: Article? = ArticleRepository.getArticle(id)
            if (article != null) {
                ArticleList("1", "0", listOf(article))
            } else {
                null
            }
        } else {
            null
        }
    }

    fun addArticle(articleRequest: ArticleRequest): ArticleList {
        val list = listOf(
            ArticleRepository.addArticle(
                articleRequest.title,
                articleRequest.desc,
                articleRequest.date
            )
        )
        return ArticleList(list.size.toString(), "0", list)
    }

    fun deleteArticle(id: Int): Boolean = ArticleRepository.deleteArticle(id)
}