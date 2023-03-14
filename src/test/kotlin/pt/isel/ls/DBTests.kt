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

    @Test
    fun `test db select`() {
        val connection = getConnection()
        try {
            connection.use {
                val stm = it.prepareStatement("select * from students")
                val rs = stm.executeQuery()
                while (rs.next()) {
                    println(rs.getString("name"))
                }
                assert(true)
            }
        } catch (e: Exception) {
            println(e)
            assert(false)
        }
    }

    @Test
    fun `test db insert`() {
        val connection = getConnection()
        try {
            connection.use {
                val stm = it.prepareStatement("insert into students(course, number, name) values (1, 12344, 'test')")
                val rs = stm.executeUpdate()
                if (rs == 0) println("No rows affected")
                assert(rs == 1)
            }
        } catch (e: Exception) {
            println(e)
            assert(false)
        }
    }

    @Test
    fun `test db update`() {
        val connection = getConnection()
        try {
            connection.use {
                val stm = it.prepareStatement("update students set name = 'test2' where number = 12344")
                val rs = stm.executeUpdate()
                if (rs == 0) println("No rows affected")
                assert(rs == 1)
            }
        } catch (e: Exception) {
            println(e)
            assert(false)
        }
    }

    /**
     * This test will fail if the previous test was not executed
     * The test is order dependent soo it's named with a z prefix to ensure it is executed last
     */
    @Test
    fun `test db zdelete`() {
        val connection = getConnection()
        try {
            connection.use {
                val stm = it.prepareStatement("delete from students where number = 12344")
                val rs = stm.executeUpdate()
                if (rs == 0) println("No rows affected")
                assert(rs == 1)
            }
        } catch (e: Exception) {
            println(e)
            assert(false)
        }
    }
}