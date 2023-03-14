package pt.isel.ls.repositories

import pt.isel.ls.domain.User
import pt.isel.ls.domain.UserInputModel

interface UserRepository {
    fun create(user: UserInputModel): User
    fun get(email: String): User
}