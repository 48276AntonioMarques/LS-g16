package pt.isel.ls

import org.postgresql.ds.PGSimpleDataSource
import java.sql.Connection
import kotlin.test.Test

class DbTests {

    private var connection: Connection? = null

    private fun getConnection(): Connection {
        if (connection != null) return connection!!
        val dataSource = PGSimpleDataSource()
        val jdbcDatabaseURL = System.getenv("JDBC_DATABASE_URL")
        dataSource.setURL(jdbcDatabaseURL)
        return dataSource.connection
    }

    @Test
    fun `test db connection`() {
        val connection = getConnection()
        try {
            connection.use {
                val stm = it.prepareStatement("select * from ( values (1) ) as t")
                val rs = stm.executeQuery()
                assert(rs.next())
            }
        } catch (e: Exception) {
            println(e)
            assert(false)
        }
    }
}