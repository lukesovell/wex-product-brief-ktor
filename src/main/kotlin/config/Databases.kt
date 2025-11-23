package github.lukesovell.config

import org.jetbrains.exposed.sql.Database

// TODO add hikari for connection pooling
fun configureDatabases(config: DatabaseConfig) {
    Database.connect(
        config.url,
        driver = "org.postgresql.Driver",
        user = config.user,
        password = config.password
    )
}