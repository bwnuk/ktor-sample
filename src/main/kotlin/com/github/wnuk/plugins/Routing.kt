package com.github.wnuk.plugins

import com.github.wnuk.entities.ArticleRequest
import com.github.wnuk.services.ArticleServices
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.features.*
import io.ktor.content.*
import io.ktor.http.content.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*

fun Application.configureRouting() {


    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("/articles/all") {
            call.respond(ArticleServices.getAllArticles())
        }

        get("/articles/single/{id}") {
            val id = call.parameters["id"]
            val result = ArticleServices.getArticle(id?.toInt())
                ?: throw NotFoundException("Didnt find any article of this id: $id")
            call.respond(result)
        }

        get("/articles/chunk/{start}/{chunk}") {
            val start = call.parameters["start"]
            val chunk = call.parameters["chunk"]
            if (start != null && chunk != null) {
                val result = ArticleServices.getArticles(start.toInt(), chunk.toInt())
                call.respond(result)
            } else {
                throw NotFoundException("Wrong start $start and chunk $chunk")
            }
        }

        post("/articles/add") {
            val post = call.receive<ArticleRequest>()
            call.respond(ArticleServices.addArticle(post))
        }

        delete("/articles/delete/{id}") {
            val id = call.parameters["id"]
            var result = false
            if (id != null) {
                result = ArticleServices.deleteArticle(id.toInt())
            }

            if (result) {
                call.respond("OK")
            } else {
//				throw NotFoundException("Didnt find any article of this id: $id")
                call.respond("NOT FOUND")
            }
        }

        install(StatusPages) {
            exception<AuthenticationException> {
                call.respond(HttpStatusCode.Unauthorized)
            }
            exception<AuthorizationException> {
                call.respond(HttpStatusCode.Forbidden)
            }

        }

        static("/static") {
            resources("static")
        }
    }
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()
