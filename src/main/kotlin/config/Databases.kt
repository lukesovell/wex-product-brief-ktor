package github.lukesovell.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL

fun configureDatabases(config: DatabaseConfig): DSLContext {
    val config = HikariConfig().apply {
        jdbcUrl = config.url
        username = config.user
        this.password = config.password
        maximumPoolSize = 10
        driverClassName = "org.postgresql.Driver"
    }
    val dataSource = HikariDataSource(config)
    return DSL.using(dataSource, SQLDialect.POSTGRES)
}