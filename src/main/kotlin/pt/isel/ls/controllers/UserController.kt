package pt.isel.ls.controllers

import pt.isel.ls.repositories.UserRepository
import org.http4k.core.Method
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import pt.isel.ls.utils.Mapping

class UserController(val userRepository: UserRepository): Controller() {

    @Mapping(path = "users", method = Method.GET)
    fun getAll(): Response {
        return Response(OK).body("getAll")
    }
}