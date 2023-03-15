package pt.isel.ls

import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.postgresql.ds.PGSimpleDataSource
import org.slf4j.LoggerFactory
import pt.isel.ls.controllers.UserController
import pt.isel.ls.repositories.jdbc.jdbcUserRepository
import pt.isel.ls.utils.generateRouter

private val logger = LoggerFactory.getLogger("pt.isel.ls.Main")

fun main() {
    val dataSource = PGSimpleDataSource()
    dataSource.setURL(System.getenv("JDBC_DATABASE_URL"))
    // dataSource.setURL("jdbc:postgresql://localhost/postgres?user=postgres&password=postgres")

    val port = 9000

    val app = generateRouter(
        listOf(
            UserController(jdbcUserRepository(dataSource))
        )
    )

    val jettyServer = app.asServer(Jetty(port)).start()
    logger.info("server started listening at: $port")

    readln()
    jettyServer.stop()

    logger.info("server stopped")
}
