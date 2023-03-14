package pt.isel.ls

import org.http4k.core.Method
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.postgresql.ds.PGSimpleDataSource
import org.slf4j.LoggerFactory
import pt.isel.ls.controllers.UserController
import pt.isel.ls.http.*
import pt.isel.ls.repositories.jdbc.jdbcUserRepository

private val logger = LoggerFactory.getLogger("pt.isel.ls.Main")

fun main() {
    val dataSource = PGSimpleDataSource()
    dataSource.setURL(System.getenv("JDBC_DATABASE_URL"))
    // dataSource.setURL("jdbc:postgresql://localhost/postgres?user=postgres&password=postgres")

    val app = routes(
        UserController(jdbcUserRepository(dataSource)).routes
    )

    // Generate routes
    // bind with handlers
    // start server

    val port = 9000

    val jettyServer = app.asServer(Jetty(port)).start()
    logger.info("server started listening at: $port")

    readln()
    jettyServer.stop()

    logger.info("server stopped")
}
