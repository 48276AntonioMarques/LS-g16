package pt.isel.ls.repositories.jdbc

import org.postgresql.ds.PGSimpleDataSource
import pt.isel.ls.domain.User
import pt.isel.ls.domain.UserInputModel
import pt.isel.ls.repositories.UserRepository

class jdbcUserRepository(val dataSource: PGSimpleDataSource): UserRepository {
    override fun create(user: UserInputModel): User {
        TODO("Not yet implemented")
    }

    override fun get(email: String): User {
        TODO("Not yet implemented")
    }
}