package pt.isel.ls.controllers

import pt.isel.ls.repositories.UserRepository
import org.http4k.core.Method
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import org.http4k.routing.routes

class UserController(val userRepository: UserRepository) {
    val routes
        get() = getRoutes()

    private fun getRoutes(): RoutingHttpHandler = routes(
        "users" bind Method.GET to ::getAll,
        "students" bind Method.GET to ::getAll,
    )

    fun getAll() {

    }
}