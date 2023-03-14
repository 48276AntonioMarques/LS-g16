package pt.isel.ls.domain

import kotlinx.serialization.Serializable
import java.util.UUID

data class User(
    val id : UUID,
    val name: String,
    val email: String
)

@Serializable
data class UserInputModel(
    val name: String,
    val email: String
) {
    fun toUser(uuid: UUID) = User(uuid, name, email)
}

@Serializable
data class UserOutputModel(
    val name: String,
    val email: String
)

fun User.toOutputModel() = UserOutputModel(name, email)